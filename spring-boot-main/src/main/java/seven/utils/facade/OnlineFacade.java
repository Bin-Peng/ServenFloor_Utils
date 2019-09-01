package seven.utils.facade;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import seven.utils.Exception.AuthException;
import seven.utils.constants.enums.ErrorMsg;
import seven.utils.constants.enums.ErrorProcessType;
import seven.utils.context.AppContext;
import seven.utils.context.RunContext;
import seven.utils.model.common.AuthRequetHeader;
import seven.utils.model.common.RunEnvs;
import seven.utils.model.table.AuthProcessControl;
import seven.utils.model.table.AuthTrxnLog;
import seven.utils.service.api.AuthParaService;
import seven.utils.service.api.AuthRespCodeMappingService;
import seven.utils.service.api.AuthService;
import seven.utils.service.api.AuthTrxnLogService;

import java.util.List;
import java.util.Map;

/**
 * 逻辑处理层
 * Created by moche_000 on 2019/8/16.
 */
@Component
public class OnlineFacade {
    private static Logger logger = LoggerFactory.getLogger(OnlineFacade.class);

    @Autowired
    private AuthParaService authParaServiceImpl;

    @Autowired
    private AuthRespCodeMappingService authRespCodeMappingService;

    @Autowired
    private AuthTrxnLogService authTrxnLogService;

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> process(Map<String, Object> request) throws Throwable{
        //根据交易码，获取授权检查组件，并按照顺序执行
        String authTrxnCode = String.valueOf(request.get(AuthRequetHeader.AUTH_TRXN_CODE));

        List<AuthProcessControl> authProcessControlList = authParaServiceImpl.queryAuthProcessControlList(authTrxnCode);

        //默认的gotoStep值，要求配置表中的step值都大于等于0
        List<String> authProcessLogs = Lists.newArrayList();

        int gotoStep = -1;
        for (AuthProcessControl authProcessControl : authProcessControlList) {
            //只要当前步骤小于gotoStep跳过，大于等于gotoStep就执行，避免还需要重置gotoStep
            if (authProcessControl.getPcStep() < gotoStep) {
                continue;
            }
            //定义组件开始时间
            long start = System.currentTimeMillis();
            try {
                //执行授权检查组件
                AuthService authService = AppContext.getBean(authProcessControl.getPcComponentBean(), AuthService.class);
                //执行组件
                authService.execute(authProcessControl);
                //统计组件耗时
                authProcessLogs.add(authProcessControl.getPcComponentBean() + ":" + (System.currentTimeMillis() - start) + "ms");
            } catch (Exception e) {
                //统计组件耗时
                authProcessLogs.add(authProcessControl.getPcComponentBean() + ":" + (System.currentTimeMillis() - start) + "ms");
                //授权处理组件异常处理类型
                ErrorProcessType errorProcessType = ErrorProcessType.getEnumByValue(authProcessControl.getPcErrorProcessType());
                //根据类型执行异常处理
                switch (errorProcessType) {
                    case FAIL:
                        //交易失败，处理失败，抛出异常
                        handleException(e);
                        //打印各组件耗时
                        throw e;
                    case CONTINUE:
                        //do nothing
                        break;
                    case GOTO:
                        //跳转
                        //获取跳转后步骤
                        Assert.notNull(authProcessControl.getPcGotoStep(), "授权业务控制表异常，异常处理类型为" + ErrorProcessType.GOTO.getValue() +"时，异常跳转步骤不能为空");
                        gotoStep = authProcessControl.getPcGotoStep();
                        //不允许跳回之前执行过的step
                        if(authProcessControl.getPcStep() >= gotoStep){
                            logger.info("[authProcessControlList]:[{}]", StringUtils.join(authProcessLogs, ";"));
                            throw new AuthException(ErrorMsg.ERROR.getErrorCode(), "授权业务规则控制表配置异常，异常跳转步骤号必须大于当前步骤号");
                        }
                        break;
                    default:
                        //交易失败，处理失败，抛出异常
                        handleException(e);
                        //打印各组件耗时
                        logger.info("[authProcessControlList]:[{}]", StringUtils.join(authProcessLogs, ";"));
                        throw e;
                }
            }
        }
        //打印各组件耗时
        logger.info("[authProcessControlList]:[{}]", StringUtils.join(authProcessLogs, ";"));
        //组件执行成功，进行成功后的处理
        handleSucess();
        return null;
    }

    //异常处理
    private void handleException(Exception e) {
        //将错误码放入上下文中
        RunEnvs runEnvs = RunContext.getRunEnvs();
        if (e instanceof AuthException) {
            //已知异常
            AuthException authException = (AuthException) e;
            //设置响应信息
            runEnvs.setRetCode(authException.getRetCode());
            runEnvs.setRetMsg(authException.getMessage());
        } else {
            //未知异常
            runEnvs.setRetCode(ErrorMsg.ERROR.getErrorCode());
            runEnvs.setRetMsg(ErrorMsg.ERROR.getMsg());
        }
        //设置渠道响应码
        runEnvs.setChannelRetCode(
                authRespCodeMappingService.responseCodeTransform(runEnvs.getRetCode(), runEnvs.getChannelId())
        );
        //独立事务登记授权流水
        authTrxnLogService.registerTrxnLogInNewTransaction(RunContext.getDataArea());
    }

    /**
     * 成功后处理
     */
    private void handleSucess() {
        RunEnvs runEnvs = RunContext.getRunEnvs();
        //设置渠道响应码
        runEnvs.setChannelRetCode(
                authRespCodeMappingService.responseCodeTransform(runEnvs.getRetCode(), runEnvs.getChannelId())
        );
        //登记授权流水
        AuthTrxnLog authTrxnLog = authTrxnLogService.registerTrxnLog(RunContext.getDataArea());
        //登记预授权流水
    }
}

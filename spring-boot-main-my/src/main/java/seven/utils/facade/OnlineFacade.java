package seven.utils.facade;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.collect.Lists;
import com.sun.xml.internal.ws.client.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 逻辑处理层
 * Created by moche_000 on 2019/8/16.
 */
@Component
public class OnlineFacade {
    private static Logger logger = LoggerFactory.getLogger(OnlineFacade.class);

    @Resource(name = "AuthParaLocal")
    private AuthParaService service;

    @Autowired
    private AuthRespCodeMappingService respCodeMappingService;

    @Autowired
    private AuthTrxnLogService logService;


    /**
     * @param params http  uri 参数
     * @param requestBody  http 请求体参数
     * @param tradeCode  交易码
     * @return
     * @throws Throwable
     */
    public Map<String, Object> process(Map<String, Object> params, Map<String, Object> requestBody, String tradeCode) throws Throwable
        {
            //根据交易码，获取授权检查组件，并按照顺序执行
            if(tradeCode==null){
                tradeCode = getHttpRequest().getRequestURI();
            }

            //单交易下，手动定义交易流程各个组件，即启动时new AuthProcessControl，然后按照顺序进行组装，组件名称提供默认，步骤需要可配置。
            List<AuthProcessControl> authProcessControlList = service.queryAuthProcessControlList(tradeCode);

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
                    AuthService authService = authProcessControl.getAuthService();
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


    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> process(Map<String, Object> params, Map<String, Object> requestBody) throws Throwable{
        return process(params,requestBody,null);
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
                respCodeMappingService.responseCodeTransform(runEnvs.getRetCode(), runEnvs.getChannelId())
        );
        //独立事务登记授权流水
        logService.registerTrxnLogInNewTransaction(RunContext.getDataArea());
    }

    /**
     * 成功后处理
     */
    private void handleSucess() {
        RunEnvs runEnvs = RunContext.getRunEnvs();
        //设置渠道响应码
        runEnvs.setChannelRetCode(
                respCodeMappingService.responseCodeTransform(runEnvs.getRetCode(), runEnvs.getChannelId())
        );
        //登记授权流水
        AuthTrxnLog authTrxnLog = logService.registerTrxnLog(RunContext.getDataArea());
        //登记预授权流水
    }


    private HttpServletRequest getHttpRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        return request;
    }
}

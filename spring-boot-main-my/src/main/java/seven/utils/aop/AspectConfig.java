package seven.utils.aop;

import com.google.common.collect.Maps;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import seven.utils.context.RunContext;
import seven.utils.model.common.RunEnvs;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一入口切面配置
 * Created by moche_000 on 2019/8/22.
 */
@Aspect
@Configuration
@Order(-1)
public class AspectConfig {

    private static Logger logger = LoggerFactory.getLogger(AspectConfig.class);


    /**
     * 需要处理的切面
     *
     * @see seven.utils.facade.OnlineFacade#process(Map, Map, String)
     */
    @Pointcut("execution(public * seven.utils.facade.OnlineFacade.process(..)) && args(params,requestBody, tradeCode) ")
    private void onlineFacadePointcut(Map<String, Object> params, Map<String, Object> requestBody, String tradeCode) {
    }

    /**
     * 切面处理方法
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("onlineFacadePointcut(params,requestBody, tradeCode)")
    public Map<String, Object> onlineFacadeAroundProcessing(ProceedingJoinPoint proceedingJoinPoint,
                                                            Map<String, Object> params, Map<String, Object> requestBody, String tradeCode) {

        //定义返回报文
        Map<String, Object> result = new HashMap<String, Object>();
        try {

            //初始化，配置信息赋值
            before(params, requestBody);
            //组件逻辑处理
            proceedingJoinPoint.proceed();
            //组装响应报文
            result = after();
        } catch (Throwable e) {
            logger.error("授权交易异常", e);
            //组装异常响应报文
            handleException();
        } finally {
            //线程任务完成之后显示调用destroy方法，避免init方法异常时线程上下文未清理
            RunContext.destroyContext();
        }
        return result;
    }

    /**
     * 主卡号查询、授权交易码配置信息赋值
     */
    private void before(Map<String, Object> params, Map<String, Object> requestBody) {
        logger.info("收到请求报文:[{}]", requestBody);
        //初始化授权信息
        if (requestBody == null) {
            requestBody = new HashMap<>();
        }
        if (params == null) {
            params = new HashMap<>();
        }
        requestBody.putAll(params);
        RunContext.initContext(requestBody);
    }


    /**
     * 组装返回报文
     *
     * @return
     */
    private Map<String, Object> after() {
        Map<String, Object> response = Maps.newHashMap();
        //组装返回报文公共字段
        RunEnvs runEnvs = RunContext.getRunEnvs();
        //交易码
        response.put("retCode", runEnvs.getRetCode());
        //...
        response.putAll(RunContext.getDataArea().getOutput());
        //返回组装后结果
        return response;
    }

    /**
     * 组装返回报文
     *
     * @return
     */
    private Map<String, Object> handleException() {
        Map<String, Object> response = Maps.newHashMap();
        //组装返回报文公共字段
        RunEnvs runEnvs = RunContext.getRunEnvs();
        //交易码
        response.put("retCode", runEnvs.getRetCode());
        //...
        response.putAll(RunContext.getDataArea().getOutput());
        //返回组装后结果
        return response;
    }
}

package seven.utils.aop;


import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import sun.plugin2.main.server.ResultHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 数据库操作性能拦截器，记录耗时
 *
 * @author pengbin
 */
@Intercepts(value = {
//        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
@Component
@ConditionalOnExpression("${performance.sql.enable:false}")
public class MybatisSqlInterceptor implements Interceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Properties properties;


    /**
     * 该对象实例被构造时，自动触发调用做预初始化
     * 也可以在bean注解后面添加initMethod 参数指定init()方法
     */
    @PostConstruct
    private void init() {
        logger.info("已开启生产sql统计耗时拦截器");
    }


    /**
     * 该对象实例被销毁时，自动触发销毁资源
     * 也可以在bean注解后面添加destroyMethod 参数指定destroy()方法
     */
    @PreDestroy
    private void destroy(){
        logger.info("已销毁生产sql统计耗时拦截器");
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String sqlId = mappedStatement.getId();
        Object returnValue;
        //打印sql耗时
        long start = System.currentTimeMillis();
        returnValue = invocation.proceed();
        long end = System.currentTimeMillis();
        long time = end - start;
        logger.info("{} cost time [{}] ms", sqlId, time);
        return returnValue;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private Properties getProperties(){
        return properties;
    }
}

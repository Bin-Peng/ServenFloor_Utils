package seven.utils.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 * Created by moche_000 on 2019/8/26.
 */
@Configuration
@ConditionalOnProperty(name = "seven.utils.threadPool.enable", matchIfMissing = false) //线程池配置默认关闭
public class ThreadPoolConfig {

    public static final String FIX_POOL = "fixPoolExecutor";
    public static final String KAFKA_POOL = "fixPoolExecutor";


    /**
     * @return AsyncTaskExecutor 是个接口
     */
    @Bean(KAFKA_POOL)
    @ConfigurationProperties(prefix = "seven.utils.kafkaPool")
    public AsyncTaskExecutor kafkaHandlerExecutor(){
        return new ThreadPoolTaskExecutor();
    }
    /**
     * @return ThreadPoolTaskExecutor 是个接口AsyncTaskExecutor的实现类，提供的方法更多一些
     */
    @Bean(FIX_POOL)
    @ConfigurationProperties(prefix = "seven.utils.fixPool")
    public ThreadPoolTaskExecutor fixPoolExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100);
        executor.setMaxPoolSize(100);
        executor.setThreadNamePrefix("seven_fixpool");
        return executor;
    }



}

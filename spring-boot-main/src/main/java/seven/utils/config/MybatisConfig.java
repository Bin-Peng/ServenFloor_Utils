package seven.utils.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;



/**
 * Mybatis Mapper Scan
 * 1.当配置了spring.datasource.url 时才会扫描，避免创建mybatis mapper bean异常
 *
 * @author pengbin
 */
@SpringBootConfiguration
@ConditionalOnProperty(name = "spring.datasource.url")
@MapperScan("seven.utils.dao")
public class MybatisConfig {

    /**
     * 数据源配置
     * @return 连接池数据源配置
     */
    @Bean(destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    /**
     * 事务控制器配置
     * @param dataSource 数据源
     * @return
     */
    @Bean(name = {"jdbcTransactionManager"})
    public PlatformTransactionManager jdbcTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}

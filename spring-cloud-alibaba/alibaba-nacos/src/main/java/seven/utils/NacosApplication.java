package seven.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName: NacosApplication <br/>
 * Description: <br/>
 * date: 2019-09-05 14:31<br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);
    }
}

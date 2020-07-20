package seven.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author pengbin
 * @EnableDiscoveryClient 实现服务注册以及服务发现能力
 */

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "seven.utils")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

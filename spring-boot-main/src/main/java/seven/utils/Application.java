package seven.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pengbin
 */
@SpringBootApplication(scanBasePackages = "seven.utils")
public class Application {
    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(new Object[]{Application.class});
        application.setEnvironment(new SevenEnviroment());
        application.run(args);
//        SpringApplication.run(Application.class, args);
    }
}

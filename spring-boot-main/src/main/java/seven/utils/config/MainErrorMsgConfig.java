package seven.utils.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * Created by moche_000 on 2019/8/21.
 */
@Configuration
@ConfigurationProperties(prefix = "error-code")
@PropertySource(value = "classpath:config/error-code-main.properties", encoding = "UTF-8")
public class MainErrorMsgConfig {
    /**
     * 主控工程错误码-错误描述配置
     */
    private Map<String, String> main;

    public Map<String, String> getMain() {
        return main;
    }

    public void setMain(Map<String, String> main) {
        this.main = main;
    }
}

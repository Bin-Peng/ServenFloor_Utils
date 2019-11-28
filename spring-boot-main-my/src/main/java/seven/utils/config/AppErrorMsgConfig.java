package seven.utils.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by moche_000 on 2019/8/21.
 */
@Configuration
@ConfigurationProperties(prefix = "error-code")
public class AppErrorMsgConfig {
    private Map<String, String> app = new HashMap<String, String>();

    public Map<String, String> getApp() {
        return app;
    }

    public void setApp(Map<String, String> app) {
        this.app = app;
    }
}

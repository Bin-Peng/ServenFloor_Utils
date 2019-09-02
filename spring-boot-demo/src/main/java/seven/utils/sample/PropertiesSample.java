package seven.utils.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import seven.utils.config.properties.SevenProperties;

@Configuration
@EnableConfigurationProperties(SevenProperties.class)
@Profile("seven")
public class PropertiesSample {
    @Autowired
    private SevenProperties properties;


}

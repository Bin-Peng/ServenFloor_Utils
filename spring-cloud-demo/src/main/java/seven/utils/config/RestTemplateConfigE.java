package seven.utils.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author pengbin
 */
@Configuration
public class RestTemplateConfigE {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        //请求超时时间
        httpRequestFactory.setConnectionRequestTimeout(1000);
        //读取超时时间
        httpRequestFactory.setReadTimeout(1000);
        //连接超时时间
        httpRequestFactory.setConnectTimeout(1000);
        return new RestTemplate(httpRequestFactory);
    }

}

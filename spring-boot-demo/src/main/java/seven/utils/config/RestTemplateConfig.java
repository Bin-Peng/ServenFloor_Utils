package seven.utils.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate的配置类，包括普通的RestTemplate、定制的RestTemplate以及@LoadBalance负载均衡的RestTemplate
 * Created by moche_000 on 2019/8/23.
 */
@Configuration
public class RestTemplateConfig {


    /**
     * 同步调用
     * 定制的RestTemplate写法，定制连接超时时间，读取超时时间，请求超时时间
     * @return
     */
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


    /**
     * RestTemplate加上@LoadBalance注解启动负载均衡
     * @return
     */
//    @Bean
//    @LoadBalanced
//    @Primary  //强制使用该公共配置，防止被其他jar引入的配置覆盖
//    RestTemplate loadBalance() {
//        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
//        //请求超时时间
//        httpRequestFactory.setConnectionRequestTimeout(1000);
//        //读取超时时间
//        httpRequestFactory.setReadTimeout(1000);
//        //连接超时时间
//        httpRequestFactory.setConnectTimeout(1000);
//        return new RestTemplate(httpRequestFactory);
//    }


    /**
     * 建议写法，没有定制好用，但是可以使用
     * @return
     */
    @Bean
    RestTemplate simpleRestTemplate(){
        return new RestTemplate();
    }
}

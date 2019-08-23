package seven.utils.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by moche_000 on 2019/8/23.
 */
@RestController
public class ConsumerController {

    //注入负载均衡的RestTemplate
    @Autowired
    private RestTemplate loadBalance;

    //注入原生的RestTemplate
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/lb")
    public String addLoadBalanced(@RequestParam Integer a, @RequestParam Integer b){
        StringBuilder requestUrl = new StringBuilder();
        requestUrl.append("http://spring-boot-demo/spring-boot-demo/provider?a=").append(a).append("&b=").append(b);
        String result = loadBalance.getForEntity(requestUrl.toString(), String.class).getBody();
        return result;
    }


}

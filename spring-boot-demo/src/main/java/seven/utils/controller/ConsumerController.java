package seven.utils.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

//    //注入负载均衡的RestTemplate
//    @Autowired
//    private RestTemplate loadBalance;

    //注入原生的RestTemplate
    @Autowired
    private RestTemplate restTemplate;


    @ApiOperation(value = "负载均衡的调用方式", notes = "使用服务的名字通过注册中心调用provider接口")
    @GetMapping("/consumer/lb")
    public String addLoadBalanced(@ApiParam(name = "a", value = "url上带参", required = true) @RequestParam Integer a, @ApiParam(name = "b", value = "url带参b", required = true) @RequestParam Integer b){
        StringBuilder requestUrl = new StringBuilder();
        requestUrl.append("http://localhost:8080/provider?a=").append(a).append("&b=").append(b);
        String result = restTemplate.getForEntity(requestUrl.toString(), String.class).getBody();
        return result;
    }


}

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
public class ProviderController {

    //注入负载均衡的RestTemplate
//    @Autowired
//    private RestTemplate loadBalance;

    //注入原生的RestTemplate
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/provider")
    public String addLoadBalanced(@RequestParam String a, @RequestParam String b){
        System.out.println(a);
        System.out.println(b);
        return "request success";
    }


}

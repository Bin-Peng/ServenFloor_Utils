package seven.utils.eureka;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author pengbin
 */
@RestController
public class EurekaService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;


    @ApiOperation(value = "负载均衡的调用方式", notes = "使用服务的名字通过注册中心调用provider接口")
    @GetMapping("/consumer/lb")
    public String addLoadBalanced(@ApiParam(name = "a", value = "url上带参", required = true) @RequestParam Integer a, @ApiParam(name = "b", value = "url带参b", required = true) @RequestParam Integer b) {
        String result = a + b + "";
        List<String> serviceNames = client.getServices();
        for (String serviceName: serviceNames){
            List<ServiceInstance> instances = client.getInstances(serviceName);
            for (ServiceInstance instance: instances){
                logger.info("注册服务：" + instance.getServiceId()+":"+instance.getUri());
            }
        }

        return result;
    }
}

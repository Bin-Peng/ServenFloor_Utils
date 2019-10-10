//package seven.utils.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
///**
// * ClassName: SevenConsumer <br/>
// * Description: <br/>
// * date: 2019-09-05 16:32 <br/>
// *
// * @author pengbin <br/>
// * @since JDK 1.8
// */
//@RestController
//public class SevenConsumer {
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private AddService addService;
//
//    @RequestMapping(value = "/consumer/add", method = RequestMethod.GET)
//    public String add() {
//        return restTemplate.getForEntity("http://nacos-server/add?a=10&b=2", String.class).getBody();
//    }
//
//    @RequestMapping(value = "/consumer/feignadd", method = RequestMethod.GET)
//    public String feignadd(@RequestParam(name = "a") Integer a, @RequestParam(name = "b") Integer b) {
//        return addService.feignadd(a, b);
//    }
//
//}

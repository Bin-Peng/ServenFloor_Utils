package seven.utils.service;

import feign.Param;
import feign.RequestLine;

/**
 * ClassName: ConsumerServiceClient <br/>
 * Description: <br/>
 * date: 2019-10-10 12:36 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public interface ConsumerServiceClient {

    @RequestLine("GET /consumer/lb?a={a}&b={b}")
    String addLoadBalanced(@Param("a") Integer a, @Param("b") Integer b);
}

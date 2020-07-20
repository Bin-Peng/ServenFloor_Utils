package seven.utils.filter;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import sun.tools.jconsole.AboutDialog;

import java.io.IOException;

/**
 * ClassName: RestTemplateHttpcliet <br/>
 * Description: <br/>
 * date: 2020/3/27 19:04 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */


public class RestTemplateHttpcliet implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        System.out.println("RestTemplate拦截器");
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}

package seven.utils.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Springboot http全局异常处理
 * Created by moche_000 on 2019/8/26.
 */
@ControllerAdvice
public class GlobalHttpExceptionProcessor {
    private Logger logger = LoggerFactory.getLogger(GlobalHttpExceptionProcessor.class);

    @ExceptionHandler(Exception.class)
    public String defaultHandleException(HttpServletRequest request, Exception e) {
        logger.error("默认异常信息");
        return "默认异常类型处理";
    }

    @ExceptionHandler(IllegalAccessException.class) //指定处理的异常
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "异常响应测试") // 修改Http响应码与响应信息
    @ResponseBody //以json的形式响应
    public Object defineHandleException(HttpServletRequest request, Exception e) {
        logger.error("自定义异常信息");
        HashMap map = new HashMap();
        return map;
    }


}

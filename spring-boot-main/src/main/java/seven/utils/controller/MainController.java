package seven.utils.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seven.utils.facade.OnlineFacade;

import java.util.Map;

/**
 * Created by moche_000 on 2019/8/22.
 */
@RestController
public class MainController {
    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private OnlineFacade facade;


    @GetMapping(path = "/spring-boot-main/auth")
    public Map<String, Object> startAuth(@RequestParam Map<String, Object> params, @RequestBody Map<String, Object> request) throws Throwable {
        logger.info("startAuth 方法调用,执行facade,参数：{}", params.values().toString());
        return facade.process(request);

    }




}

package seven.utils.service.sampleimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import seven.utils.model.table.AuthProcessControl;
import seven.utils.service.api.AuthService;

@Component
public class AuthServiceImpl2 implements AuthService{
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl2.class);
    @Override
    public void execute(AuthProcessControl authProcessControl) {
        logger.info("AuthServiceImpl2 执行");
    }
}

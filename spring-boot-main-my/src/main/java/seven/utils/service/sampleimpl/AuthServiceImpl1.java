package seven.utils.service.sampleimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import seven.utils.dto.AuthServiceImpl1DTO;
import seven.utils.dto.AuthServiceImpl2DTO;
import seven.utils.model.table.AuthProcessControl;
import seven.utils.service.api.AuthService;

@Component
public class AuthServiceImpl1 implements AuthService<AuthServiceImpl1DTO>{
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl1.class);


    @Override
    public void execute(AuthServiceImpl1DTO requestDTO, AuthProcessControl authProcessControl) {
        logger.info("{} 执行",requestDTO.getClass().getSimpleName());
    }

    @Override
    public void exceptionHandle() {

    }

    @Override
    public Class<AuthServiceImpl1DTO> getRequestClass() {
        return AuthServiceImpl1DTO.class;
    }
}

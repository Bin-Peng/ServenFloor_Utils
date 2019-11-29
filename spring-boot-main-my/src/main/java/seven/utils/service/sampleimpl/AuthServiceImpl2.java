package seven.utils.service.sampleimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import seven.utils.dto.AuthServiceImpl1DTO;
import seven.utils.dto.AuthServiceImpl2DTO;
import seven.utils.model.table.AuthProcessControl;
import seven.utils.service.api.AuthService;

@Component
public class AuthServiceImpl2 implements AuthService<AuthServiceImpl2DTO> {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl2.class);


    @Override
    public void execute(AuthServiceImpl2DTO request, AuthProcessControl authProcessControl) {
        logger.info("{} 执行",request.getClass().getSimpleName());
    }

    @Override
    public void exceptionHandle() {

    }
    @Override
    public Class<AuthServiceImpl2DTO> getRequestClass() {
        return AuthServiceImpl2DTO.class;
    }
}

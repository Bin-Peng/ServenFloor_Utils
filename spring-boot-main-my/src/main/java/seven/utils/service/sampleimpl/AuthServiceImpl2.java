package seven.utils.service.sampleimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import seven.utils.dto.AuthServiceImpl1DTO;
import seven.utils.dto.AuthServiceImpl2DTO;
import seven.utils.model.table.AuthProcessControl;
import seven.utils.service.api.AbstractAuthService;
import seven.utils.service.api.AuthService;

@Component
public class AuthServiceImpl2 extends AbstractAuthService<AuthServiceImpl1DTO,AuthServiceImpl1DTO> {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl1.class);


    @Override
    protected AuthServiceImpl1DTO doExecute(AuthServiceImpl1DTO requestDTO, AuthProcessControl authProcessControl) {
        return null;
    }

    @Override
    public void exceptionHandle() {

    }
}
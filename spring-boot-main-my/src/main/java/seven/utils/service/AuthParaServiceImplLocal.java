package seven.utils.service;

import org.springframework.stereotype.Service;
import seven.utils.init.ProcessControllerLoader;
import seven.utils.model.table.AuthProcessControl;
import seven.utils.model.table.AuthProcessControlKey;
import seven.utils.service.api.AuthParaService;

import java.util.List;

/**
 * ClassName: Auth <br/>
 * Description: <br/>
 * date: 2019/11/28 19:48 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
@Service(value = "AuthParaLocal")
public class AuthParaServiceImplLocal implements AuthParaService {

    @Override
    public List<AuthProcessControl> queryAuthProcessControlList(String authTrxnCode) {

        return ProcessControllerLoader.getTrade(authTrxnCode);
    }
}

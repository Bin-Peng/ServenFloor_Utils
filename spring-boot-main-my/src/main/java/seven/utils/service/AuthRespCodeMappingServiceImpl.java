package seven.utils.service;

import org.springframework.stereotype.Service;
import seven.utils.service.api.AuthRespCodeMappingService;


/**
 * 响应码转换查询处理
 * @author pengbin
 */
@Service
public class AuthRespCodeMappingServiceImpl implements AuthRespCodeMappingService {


    @Override
    public String responseCodeTransform(String errorCode, String channelId) {
        return null;
    }

    @Override
    public String queryDetailCode(String errorCode) {
        return null;
    }

    @Override
    public String queryInternalRespCode(String errorCode) {
        return null;
    }
}

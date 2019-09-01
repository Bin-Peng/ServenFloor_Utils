package seven.utils.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import seven.utils.dao.AuthProcessControlMapperBak;
import seven.utils.service.api.AuthParaService;

import java.util.List;

/**
 * Created by moche_000 on 2019/8/23.
 */
@Component
public class AuthParaServiceImpl implements AuthParaService{

    @Autowired
    private AuthProcessControlMapperBak authProcessControlMapperBak;


    /**
     * 获取授权交易码对应授权业务规则控制列表
     * @param authTrxnCode 授权交易码
     * @return  添加@Cacheable 缓存机制，每次访问优先缓存，避免重复读取数据库
     */
    @Override
    @Cacheable(value = "authProcessControlCache")
    public List<AuthProcessControl> queryAuthProcessControlList(String authTrxnCode) {
        return authProcessControlMapperBak.selectControlListAsc(authTrxnCode);
    }
}

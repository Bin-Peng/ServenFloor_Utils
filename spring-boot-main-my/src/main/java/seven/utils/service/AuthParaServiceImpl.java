package seven.utils.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import seven.utils.dao.AuthProcessControlMapper;
import seven.utils.model.table.AuthProcessControl;
import seven.utils.model.table.AuthProcessControlKey;
import seven.utils.service.api.AuthParaService;

import java.util.List;

/**
 * @author pengbin
 * Created by moche_000 on 2019/8/23.
 * 业务类  在DAO类之上封装方法，对查询之后的数据进行处理
 */
//@Service
public class AuthParaServiceImpl implements AuthParaService{

    @Autowired
    private AuthProcessControlMapper authProcessControlMapper;


    /**
     * 获取授权交易码对应授权业务规则控制列表
     * @param authTrxnCode 授权交易码
     * @return  添加@Cacheable 缓存机制，每次访问优先缓存，避免重复读取数据库
     */
    @Override
    @Cacheable(value = "authProcessControlCache")
    public List<AuthProcessControl> queryAuthProcessControlList(String authTrxnCode) {
        AuthProcessControlKey key = new AuthProcessControlKey();
        key.setTradeCode(authTrxnCode);
        return authProcessControlMapper.selectControlListAsc(key);
    }
}

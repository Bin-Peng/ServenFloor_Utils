package seven.utils.service;

import seven.utils.model.table.AuthProcessControl;

import java.util.List;

/**
 * 公共缓存service
 * Created by moche_000 on 2019/8/16.
 */
public interface AuthParaService {

    /**
     * 根据交易码获取交易码对应授权业务规则控制表
     * @param authTrxnCode 授权交易码
     * @return 授权交易码对应授权业务规则控制表
     */
    List<AuthProcessControl> queryAuthProcessControlList(String authTrxnCode);
}

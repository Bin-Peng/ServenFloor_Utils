package seven.utils.dao;

import seven.utils.model.table.AuthProcessControl;

import java.util.List;

/**
 * 授权业务规则控制表
 * Created by moche_000 on 2019/8/23.
 */
public interface AuthProcessControlMapper {

    /**
     * 根据交易码查询对应的授权业务规则处理列表，按步骤号升序排列
     * @param pcAuthTrxnCode
     * @return
     */
    List<AuthProcessControl> selectControlListAsc(String pcAuthTrxnCode);
}

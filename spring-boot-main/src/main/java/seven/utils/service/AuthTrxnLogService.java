package seven.utils.service;

import seven.utils.model.common.DataArea;
import seven.utils.model.properties.OriginalTrxnCondition;
import seven.utils.model.table.AuthTrxnLog;

/**
 * 授权流水处理service
 *
 * Created by moche_000 on 2019/8/21.
 */
public interface AuthTrxnLogService {


    /**
     * 登记授权流水
     * @param dataArea
     * @return
     */
    AuthTrxnLog registerTrxnLog(DataArea dataArea);


    /**
     * 独立事务等级授权流水
     * @param dataArea
     * @return
     */
    AuthTrxnLog registerTrxnLogInNewTransaction(DataArea dataArea);


    /**
     * 更新原交易流水状态
     * @param original 授权流水
     */
    void updateOriginalTrxnLogStatus(AuthTrxnLog original);


    /**
     * 查找原交易
     * @param condition 原交易查找条件
     * @return
     */
    AuthTrxnLog selectOriginalTrxn(OriginalTrxnCondition condition);

    /**
     * 查找原交易
     * @param tlAuthTrxnSeq
     * @return
     */
    AuthTrxnLog selectByAuthTrxnSeq(String tlAuthTrxnSeq);
}

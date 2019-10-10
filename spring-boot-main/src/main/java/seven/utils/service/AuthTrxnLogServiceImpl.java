package seven.utils.service;

import org.springframework.stereotype.Service;
import seven.utils.model.common.DataArea;
import seven.utils.model.properties.OriginalTrxnCondition;
import seven.utils.model.table.AuthTrxnLog;
import seven.utils.service.api.AuthTrxnLogService;


/**
 * @author pengbin
 */
@Service
public class AuthTrxnLogServiceImpl implements AuthTrxnLogService {
    @Override
    public AuthTrxnLog registerTrxnLog(DataArea dataArea) {
        return null;
    }

    @Override
    public AuthTrxnLog registerTrxnLogInNewTransaction(DataArea dataArea) {
        return null;
    }

    @Override
    public void updateOriginalTrxnLogStatus(AuthTrxnLog original) {

    }

    @Override
    public AuthTrxnLog selectOriginalTrxn(OriginalTrxnCondition condition) {
        return null;
    }

    @Override
    public AuthTrxnLog selectByAuthTrxnSeq(String tlAuthTrxnSeq) {
        return null;
    }
}

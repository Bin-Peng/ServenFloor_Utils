package seven.utils.model.table;

import java.io.Serializable;

/**
 * 授权业务规则控制表
 * Created by moche_000 on 2019/8/16.
 */
public class AuthProcessControlKey implements Serializable {

    /**
     * 授权交易代码
     */
    private String pcAuthTrxnCode;

    /**
     * 执行步骤
     */
    private Integer pcStep;

    private static final long serialVersionUID = 1L;

    public String getPcAuthTrxnCode() {
        return pcAuthTrxnCode;
    }

    public void setPcAuthTrxnCode(String pcAuthTrxnCode) {
        this.pcAuthTrxnCode = pcAuthTrxnCode;
    }

    public Integer getPcStep() {
        return pcStep;
    }

    public void setPcStep(Integer pcStep) {
        this.pcStep = pcStep;
    }
}

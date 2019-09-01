package seven.utils.model.table;

import java.io.Serializable;

/**
 * Created by moche_000 on 2019/8/21.
 */
public class AuthTrxnLogKey implements Serializable {
    /**
     * 分页客户号
     */
    private String mainCustomerId;
    /**
     * 授权流水号
     */
    private String tlAuthTrxnSeq;
    /**
     * 授权系统日期
     */
    private String tlAuthDate;

    private static final long serialVersionUID = 1L;

    public String getMainCustomerId() {
        return mainCustomerId;
    }

    public void setMainCustomerId(String mainCustomerId) {
        this.mainCustomerId = mainCustomerId;
    }

    public String getTlAuthTrxnSeq() {
        return tlAuthTrxnSeq;
    }

    public void setTlAuthTrxnSeq(String tlAuthTrxnSeq) {
        this.tlAuthTrxnSeq = tlAuthTrxnSeq;
    }

    public String getTlAuthDate() {
        return tlAuthDate;
    }

    public void setTlAuthDate(String tlAuthDate) {
        this.tlAuthDate = tlAuthDate;
    }
}

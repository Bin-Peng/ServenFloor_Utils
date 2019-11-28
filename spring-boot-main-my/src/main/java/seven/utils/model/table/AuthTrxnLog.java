package seven.utils.model.table;

import java.io.Serializable;

/**
 * Created by moche_000 on 2019/8/21.
 */
public class AuthTrxnLog extends AuthTrxnLogKey implements Serializable{
    /**
     * 授权交易代码
     */
    private String tlAuthTrxnCode;
    /**
     * 渠道
     */
    private String tlChannleId;
    /**
     * 消息类型
     */
    private String tlMessageType;

    /**
     * 报文处理码
     */
    private String tlProcessingCode;


    public String getTlAuthTrxnCode() {
        return tlAuthTrxnCode;
    }

    public void setTlAuthTrxnCode(String tlAuthTrxnCode) {
        this.tlAuthTrxnCode = tlAuthTrxnCode;
    }

    public String getTlChannleId() {
        return tlChannleId;
    }

    public void setTlChannleId(String tlChannleId) {
        this.tlChannleId = tlChannleId;
    }

    public String getTlMessageType() {
        return tlMessageType;
    }

    public void setTlMessageType(String tlMessageType) {
        this.tlMessageType = tlMessageType;
    }

    public String getTlProcessingCode() {
        return tlProcessingCode;
    }

    public void setTlProcessingCode(String tlProcessingCode) {
        this.tlProcessingCode = tlProcessingCode;
    }
}

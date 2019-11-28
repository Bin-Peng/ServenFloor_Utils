package seven.utils.model.common;

/**
 * Created by moche_000 on 2019/8/15.
 * 交易上下文信息
 */
public class RunEnvs {

    /**
     * 交易码
     */
    private String trxnCode;
    /**
     * 内部响应码
     */
    private String retCode;
    /**
     * 内部响应信息
     */
    private String retMsg;
    /**
     * 渠道号
     */
    private String channelId;
    /**
     * 渠道响应码
     */
    private String channelRetCode;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelRetCode() {
        return channelRetCode;
    }

    public void setChannelRetCode(String channelRetCode) {
        this.channelRetCode = channelRetCode;
    }

    public String getTrxnCode() {
        return trxnCode;
    }

    public void setTrxnCode(String trxnCode) {
        this.trxnCode = trxnCode;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}

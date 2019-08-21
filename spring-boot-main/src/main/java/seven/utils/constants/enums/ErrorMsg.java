package seven.utils.constants.enums;

import org.apache.commons.lang3.StringUtils;
import seven.utils.config.AppErrorMsgConfig;
import seven.utils.config.MainErrorMsgConfig;
import seven.utils.context.AppContext;

import java.util.Map;

/**
 * Created by moche_000 on 2019/8/21.
 */
public enum ErrorMsg {


    ERROR("99999", "系统异常");
    /**
     * 错误信息
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String mesg;

    /**
     * 响应码信息的格式
     */
    private boolean format;

    ErrorMsg(String errorCode, String mesg, boolean format) {
        this.errorCode = errorCode;
        this.mesg = mesg;
        this.format = format;
    }

    ErrorMsg(String errorCode, String mesg) {
        this(errorCode, mesg,false);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMesg() {
        return mesg;
    }


    /**
     * @return 获取错误码-错误描述中当前错误码的描述信息，不存在则返回“系统异常：未配置错误信息”
     */
    public String getMsg(){
        //获取App中配置的错误码-错误描述映射
        Map<String, String> appErrorMsgMap = AppContext.getBean(AppErrorMsgConfig.class).getApp();
        //获取当前jar包中配置的错误码-错误描述映射
        Map<String, String> mainErrorMsgMap = AppContext.getBean(MainErrorMsgConfig.class).getMain();
        return StringUtils.defaultString(appErrorMsgMap.get(errorCode),
                StringUtils.defaultString(mainErrorMsgMap.get(errorCode), "系统异常:未配置错误信息"));
    }



}

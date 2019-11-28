package seven.utils.Exception;


import seven.utils.constants.enums.ErrorMsg;

/**
 * Created by moche_000 on 2019/8/21.
 * 授权主控异常类
 * 组件异常继承改异常，在最外层调用直接捕获该异常获取响应信息，未捕获到，则统一置为系统异常
 */
public class AuthException extends RuntimeException{
    /**
     * 响应码
     */
    protected String retCode;

    public String getRetCode() {
        return retCode;
    }

    public AuthException(ErrorMsg errorMsg){
        this(errorMsg.getErrorCode(),errorMsg.getMsg());
    }


    public AuthException(String retCode, String errorMsg) {
        super(errorMsg);
        this.retCode = retCode;
    }

    public AuthException() {
        super();
    }

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(Throwable cause) {
        super(cause);
    }


}

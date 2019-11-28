package seven.utils.constants.enums;

import org.springframework.util.Assert;

/**
 * Created by moche_000 on 2019/8/21.
 */
public enum ErrorProcessType {
    FAIL("01", "交易失败"),
    CONTINUE("02", "交易继续"),
    GOTO("03", "跳转");

    private String value;
    private String desc;

    ErrorProcessType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }


    public String getDesc() {
        return desc;
    }

    public static ErrorProcessType getEnumByValue(String value) {
        ErrorProcessType result = null;
        for (ErrorProcessType e : ErrorProcessType.values()) {
            if (e.getValue().equals(value)) {
                result = e;
                break;
            }
        }
        Assert.notNull(result, "cannot find ErrorProcessType of value " + value);
        return result;
    }

}

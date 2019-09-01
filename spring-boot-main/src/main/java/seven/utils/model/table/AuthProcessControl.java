package seven.utils.model.table;

import java.io.Serializable;

/**
 * Created by moche_000 on 2019/8/16.
 */
public class AuthProcessControl extends  AuthProcessControlKey implements Serializable{
    /**
     * 授权组件bean
     */
    private String pcComponentBean;
    /**
     * 授权组件名称
     */
    private String pcComponentName;


    /**
     * 异常处理类型
     */
    private String pcErrorProcessType;
    /**
     * 异常跳转步骤
     */
    private Integer pcGotoStep;
    /**
     * 创建时间
     */
    private String pcCreateTime;

    private String pcCreateUser;
    /**
     * 更新时间
     */
    private String pcUpdateTime;
    /**
     * 更新用户
     */
    private String pcUpdateUser;
    private String pcVersion;



    private static final long serialVersionUID = 1L;


    public String getPcVersion() {
        return pcVersion;
    }

    public void setPcVersion(String pcVersion) {
        this.pcVersion = pcVersion;
    }

    public String getPcCreateUser() {
        return pcCreateUser;
    }

    public void setPcCreateUser(String pcCreateUser) {
        this.pcCreateUser = pcCreateUser;
    }

    public AuthProcessControl() {
    }

    public String getPcComponentBean() {
        return pcComponentBean;
    }

    public void setPcComponentBean(String pcComponentBean) {
        this.pcComponentBean = pcComponentBean;
    }

    public String getPcComponentName() {
        return pcComponentName;
    }

    public void setPcComponentName(String pcComponentName) {
        this.pcComponentName = pcComponentName;
    }

    public String getPcErrorProcessType() {
        return pcErrorProcessType;
    }

    public void setPcErrorProcessType(String pcErrorProcessType) {
        this.pcErrorProcessType = pcErrorProcessType;
    }

    public void setPcGotoStep(Integer pcGotoStep) {
        this.pcGotoStep = pcGotoStep;
    }

    public Integer getPcGotoStep() {
        return pcGotoStep;
    }

    public String getPcCreateTime() {
        return pcCreateTime;
    }

    public void setPcCreateTime(String pcCreateTime) {
        this.pcCreateTime = pcCreateTime;
    }

    public String getPcUpdateTime() {
        return pcUpdateTime;
    }

    public void setPcUpdateTime(String pcUpdateTime) {
        this.pcUpdateTime = pcUpdateTime;
    }

    public String getPcUpdateUser() {
        return pcUpdateUser;
    }

    public void setPcUpdateUser(String pcUpdateUser) {
        this.pcUpdateUser = pcUpdateUser;
    }
}

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
    private String pcGotoStep;
    /**
     * 创建时间
     */
    private String pcCreateTime;
    /**
     * 更新时间
     */
    private String pcUppdateTime;
    /**
     * 更新用户
     */
    private String pcUppdateUser;

    private static final long serialVersionUID = 1L;
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

    public String getPcGotoStep() {
        return pcGotoStep;
    }

    public void setPcGotoStep(String pcGotoStep) {
        this.pcGotoStep = pcGotoStep;
    }

    public String getPcCreateTime() {
        return pcCreateTime;
    }

    public void setPcCreateTime(String pcCreateTime) {
        this.pcCreateTime = pcCreateTime;
    }

    public String getPcUppdateTime() {
        return pcUppdateTime;
    }

    public void setPcUppdateTime(String pcUppdateTime) {
        this.pcUppdateTime = pcUppdateTime;
    }

    public String getPcUppdateUser() {
        return pcUppdateUser;
    }

    public void setPcUppdateUser(String pcUppdateUser) {
        this.pcUppdateUser = pcUppdateUser;
    }
}

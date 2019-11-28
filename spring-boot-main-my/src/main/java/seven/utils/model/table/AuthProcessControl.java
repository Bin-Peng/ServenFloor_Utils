package seven.utils.model.table;

import org.springframework.beans.factory.annotation.Autowired;
import seven.utils.service.api.AuthService;

import java.io.Serializable;

/**
 * Created by moche_000 on 2019/8/16.
 */
public class AuthProcessControl extends AuthProcessControlKey implements Serializable {
    /**
     * 授权组件bean
     */
    private AuthService authService;

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


    private static final long serialVersionUID = 1L;


    public AuthProcessControl() {
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
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
}

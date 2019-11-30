package seven.utils.service.api;

import seven.utils.model.table.AuthProcessControl;

/**
 * 组件service接口类
 * Created by moche_000 on 2019/8/16.
 */
public interface AuthService {
    /**
     * 授权控制入口
     *
     * @param authProcessControl 授权控制规则
     */
    void execute(AuthProcessControl authProcessControl);


    /**
     * 执行异常情况
     */
    void exceptionHandle();

}

package seven.utils.service.api;

import seven.utils.model.table.AuthProcessControl;

/**
 * 组件service接口类
 * Created by moche_000 on 2019/8/16.
 */
public interface AuthService<T> {
    /**
     * 授权控制入口
     *
     * @param requestDTO         请求参数模型
     * @param authProcessControl 授权控制规则
     */
    void execute(T requestDTO, AuthProcessControl authProcessControl);


    /**
     * 执行异常情况
     */
    void exceptionHandle();

    /**
     * 返回请求参数类型
     *
     * @return
     */
    Class<T> getRequestClass();
}

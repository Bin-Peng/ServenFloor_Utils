package seven.utils.service.trade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import seven.utils.model.table.AuthProcessControl;
import seven.utils.service.sampleimpl.AuthServiceImpl1;

/**
 * ClassName: SampleTrade1 <br/>
 * Description: <br/>
 * 需求：
 * 1. 开发者只需要关注交易流程的排序
 * 2. 交易流程分交易组件以及流程组件，流程组件定义step顺序，流程名称
 * 3. 屏蔽交易最后的装载过程。
 * date: 2019/11/28 20:08 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
@Component
public class SampleTrade1 extends AbstractTrade {

    private static final String tradeCode = "/spring-boot-main/auth";

    @Autowired
    private AuthServiceImpl1 authServiceImpl1;
    @Autowired
    private AuthServiceImpl1 authServiceImpl2;

    @Override
    void initTrade() {
        AuthProcessControl processControl1 = new AuthProcessControl();
        processControl1.setPcStep(1);
        processControl1.setPcComponentName("SampleTrade1处理步骤1");
        processControl1.setAuthService(authServiceImpl1);
        addProcessControl(processControl1);

        AuthProcessControl processControl2 = new AuthProcessControl();
        processControl2.setPcStep(2);
        processControl2.setPcComponentName("SampleTrade1处理步骤2");
        processControl2.setAuthService(authServiceImpl2);
        addProcessControl(processControl2);
    }

    @Override
    String getTradeCode() {
        return tradeCode;
    }
}

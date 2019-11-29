package seven.utils.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import seven.utils.init.ProcessControllerLoader;
import seven.utils.model.table.AuthProcessControl;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: AbstractTrade <br/>
 * Description: <br/>
 * date: 2019/11/28 20:25 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public abstract class AbstractTrade implements ApplicationRunner {

    private List<AuthProcessControl> trade = new ArrayList<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initTrade();
        ProcessControllerLoader.putTrade(getTradeCode(), trade);
    }

    /**
     * 组装并初始化交易流程
     */
    protected abstract void initTrade();

    /**
     * 顺序添加交易流程组件
     * @param authProcessControl
     */
    protected void addProcessControl(AuthProcessControl authProcessControl) {
        authProcessControl.setTradeCode(getTradeCode());
        trade.add(authProcessControl);
    }

    /**
     * 定义交易码名称，与http接口保持一致
     * @return
     */
    protected abstract String getTradeCode();

}

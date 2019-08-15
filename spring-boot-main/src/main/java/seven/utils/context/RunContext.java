package seven.utils.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import seven.utils.model.common.DataArea;
import seven.utils.model.common.RunEnvs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by moche_000 on 2019/8/15.
 */
public class RunContext {
    private static final Logger logger = LoggerFactory.getLogger(RunContext.class);

    /**
     * 禁止外部构造
     */
    private RunContext() {
    }

    private static ThreadLocal<DataArea> dataAreas = new ThreadLocal<DataArea>();
    private static ThreadLocal<RunEnvs> runEnvsThreadLocal = new ThreadLocal<RunEnvs>();


    /**
     * 初始化上下文
     *
     * @param request
     * @param mainCustomerId
     */
    public static void initContext(Map<String, Object> request, String mainCustomerId, boolean virtualCardFlag) {
        Assert.notEmpty(request, "request can not be empty");
        DataArea dataArea = new DataArea();
        //将request放入dataArea中输入区
        Map<String, Object> input = new HashMap<String, Object>(64);
        input.putAll(request);
        dataArea.setInput(input);
        //初始化数据区
        dataArea.initData(64);
        dataAreas.set(dataArea);

        //公共上下文对象
        RunEnvs envs = new RunEnvs();
        runEnvsThreadLocal.set(envs);
    }

    /**
     * 线程任务完成之后显示调用destroy方法，避免init方法异常时线程上下文未清理
     */
    public static void destroyContext() {
        //清理数据区
        dataAreas.remove();
        //清理上下文
        runEnvsThreadLocal.remove();
    }

    /**
     * 取交易数据区
     */
    public static DataArea getDataArea() {
        //获取数据区
        DataArea dataArea = dataAreas.get();
        Assert.notNull(dataArea, "DataArea is null");
        return dataArea;
    }

    /**
     * 取上下文对象
     */
    public static RunEnvs getRunEnvs() {
        //获取数据区
        RunEnvs runEnvs = runEnvsThreadLocal.get();
        Assert.notNull(runEnvs, "DataArea is null");
        return runEnvs;
    }

}

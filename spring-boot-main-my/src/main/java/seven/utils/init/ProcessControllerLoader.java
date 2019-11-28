package seven.utils.init;

import seven.utils.model.table.AuthProcessControl;
import seven.utils.model.table.AuthProcessControlKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: ProcessControllerLoader <br/>
 * Description: <br/>
 * date: 2019/11/28 19:54 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public class ProcessControllerLoader {


    private static Map<String, List<AuthProcessControl>> tradeList = new ConcurrentHashMap<>();

    /**
     * 调用接口
     *
     * @return 组装后的完整交易流程
     */
    public static List<AuthProcessControl> getTrade(String authProcessControlKey) {
        return tradeList.get(authProcessControlKey);
    }


    /**
     * 初始化交易流程
     * @param tradeCode 交易码  唯一标识
     * @param trade 交易流程
     */
    public static void putTrade(String tradeCode, List<AuthProcessControl> trade){
        if(tradeList.containsKey(tradeCode)){
            throw new IllegalArgumentException("tradeCode 重复，请检查！tradeCode:"+tradeCode);
        }
        tradeList.put(tradeCode, trade);
    }



}

package seven.utils.state.base;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: StateRecord <br/>
 * Description: 状态节点轨迹<br/>
 * date: 2020/7/21 11:51 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public class StateRecord {

    private Integer currNum = new Integer(0);
    public Map<Integer, State> state = new HashMap<>();

    public Map<Integer, State> getState() {
        return state;
    }

    public void addState(State newState) {
        currNum = currNum.intValue()+1;
        state.put(currNum, newState);
    }


}

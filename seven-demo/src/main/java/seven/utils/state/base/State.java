package seven.utils.state.base;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: State <br/>
 * Description: 状态节点<br/>
 * date: 2020/7/20 20:30 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public class State {

    /**
     * 状态编码
     */
    private String stateCode;
    private List<Transition> transitions = new ArrayList<>();

    public State(String stateCode, Transition... transitions) {
        this.stateCode = stateCode;
        for (Transition transition : transitions) {
            this.transitions.add(transition);
        }
    }


    /**
     * 添加动作
     */
    public void addTransition(Transition transition) {
        this.transitions.add(transition);
    }

    @Override
    public String toString() {
        return stateCode.toString();
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }
}

package seven.utils.state.base;

import java.util.List;

/**
 * ClassName: StateMachine <br/>
 * Description: 状态机，维护该状态机所有支持的状态，以及调用入口<br/>
 * date: 2020/7/20 20:50 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public abstract class StateMachine {
    /**
     * 定义的所有状态
     */
    private static List<State> allStates = null;

    public State execute(String stateCode, Event event){
        State startState = this.getState(stateCode);
        for(Transition transition :startState.getTransitions()){
            if(event.getEventCode().equals(transition.getEventCode())){
                return transition.execute(event);
            }
        }
        System.out.println("StateMachine["+this.getClass().getSimpleName()+"] Can not find transition for stateId["+stateCode+"] eventCode["+event.getEventCode()+"]");
        return null;
    }


    public State getState(String stateCode){
        if(allStates == null){
            System.out.println("状态机状态初始化");
            allStates  = this.declareAllStates();
        }
        for (State state : allStates){
            if(state.getStateCode().equals(stateCode)){
                return state;
            }
        }
        return null;
    }

    public abstract List<State> declareAllStates();
 }

package seven.utils.state.base;

/**
 * ClassName: Transition <br/>
 * Description: 动作基类<br/>
 * date: 2020/7/20 20:33 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public abstract class Transition {

    /**
     * 触发事件
     */
    private String eventCode;

    /**
     * 触发前状态
     */
    private State currState;

    /**
     * 触发后状态
     */
    private State nextState;

    public Transition(String eventCode, State currState, State nextState) {
        this.eventCode = eventCode;
        this.currState = currState;
        this.nextState = nextState;
    }


    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public State getCurrState() {
        return currState;
    }

    public void setCurrState(State currState) {
        this.currState = currState;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    /**
     * 执行动作
     */

    public State execute(Event event) {
        System.out.println(String.format("当前是：%s 状态，执行：%s 操作后，流转成：%s 状态。", currState, eventCode, nextState));
        if(this.doExecute(event)){
            return this.nextState;
        } else {
            return null;
        }
    }

    /**
     * 执行动作的具体业务
     * @param event
     * @return
     */
    protected abstract boolean doExecute(Event event);
}

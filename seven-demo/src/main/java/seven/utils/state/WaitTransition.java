package seven.utils.state;

import seven.utils.state.base.Event;
import seven.utils.state.base.State;
import seven.utils.state.base.Transition;
import seven.utils.state.constants.EventCodeContents;

/**
 * ClassName: WaitTransition <br/>
 * Description: <br/>
 * date: 2020/7/21 13:02 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public class WaitTransition extends Transition {
    public WaitTransition(State currState, State nextState) {
        super(EventCodeContents.WAIT, currState, nextState);
    }

    @Override
    protected boolean doExecute(Event event) {
        System.out.println("执行延迟审批操作");
        return true;
    }
}

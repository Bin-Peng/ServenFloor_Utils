package seven.utils.state;

import seven.utils.state.base.Event;
import seven.utils.state.base.State;
import seven.utils.state.base.Transition;
import seven.utils.state.constants.EventCodeContents;

/**
 * ClassName: PassTransition <br/>
 * Description: 通过动作<br/>
 * date: 2020/7/21 10:34 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public class PassTransition extends Transition {
    public PassTransition(State currState, State nextState) {
        super(EventCodeContents.PASSED, currState, nextState);
    }

    @Override
    protected boolean doExecute(Event event) {
        System.out.println("执行通过动作");
        return true;
    }
}

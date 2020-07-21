package seven.utils.state;

import seven.utils.state.base.Event;
import seven.utils.state.base.State;
import seven.utils.state.base.Transition;
import seven.utils.state.constants.EventCodeContents;

/**
 * ClassName: RefuseTransition <br/>
 * Description: <br/>
 * date: 2020/7/21 10:38 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public class RefuseTransition extends Transition {
    public RefuseTransition(State currState, State nextState) {
        super(EventCodeContents.REFUSED, currState, nextState);
    }

    @Override
    protected boolean doExecute(Event event) {
        System.out.println("执行拒绝动作");
        return true;
    }
}

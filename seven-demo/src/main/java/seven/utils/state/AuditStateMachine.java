package seven.utils.state;

import seven.utils.state.base.State;
import seven.utils.state.base.StateMachine;
import seven.utils.state.constants.StateCodeContents;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: AuditStateMachine <br/>
 * Description: 请假状态机<br/>
 * date: 2020/7/21 10:28 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public class AuditStateMachine extends StateMachine {
    //定义请假状态机状态
    @Override
    public List<State> declareAllStates() {
        List<State> stateList = new ArrayList<>();
        State pendingState = createState(StateCodeContents.PENDING);
        State passedState = createState(StateCodeContents.PASSED);
        State refusedState = createState(StateCodeContents.REFUSED);
        State waitState = createState(StateCodeContents.WAIT);


        pendingState.addTransition(new PassTransition(pendingState, passedState));
        pendingState.addTransition(new RefuseTransition(pendingState, refusedState));
        pendingState.addTransition(new WaitTransition(pendingState, waitState));

        passedState.addTransition(new RefuseTransition(passedState, refusedState));
        passedState.addTransition(new WaitTransition(passedState, waitState));

        refusedState.addTransition(new PassTransition(refusedState, passedState));
        refusedState.addTransition(new WaitTransition(refusedState, waitState));


        stateList.add(pendingState);
        stateList.add(passedState);
        stateList.add(refusedState);

        return stateList;
    }

    private State createState(String stateCode){
        return new State(stateCode);
    }
}

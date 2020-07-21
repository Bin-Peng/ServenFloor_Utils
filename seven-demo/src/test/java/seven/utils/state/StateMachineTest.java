package seven.utils.state;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import seven.utils.state.base.Event;
import seven.utils.state.base.State;
import seven.utils.state.base.StateMachine;
import seven.utils.state.constants.EventCodeContents;
import seven.utils.state.constants.StateCodeContents;

/**
 * StateMachine Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>7月 21, 2020</pre>
 */
public class StateMachineTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: execute(String stateCode, Event event)
     */
    @Test
    public void testExecute() throws Exception {
        StateMachine sm = new AuditStateMachine();
        State state = sm.execute(StateCodeContents.PENDING, new Event(EventCodeContents.PASSED));
        Assert.assertEquals(state.getStateCode(), StateCodeContents.PASSED);

        State state1 = sm.execute(state.getStateCode(), new Event(EventCodeContents.REFUSED));
        Assert.assertEquals(state1.getStateCode(), StateCodeContents.REFUSED);

        State state3 = sm.execute(state1.getStateCode(), new Event(EventCodeContents.WAIT));
        Assert.assertEquals(state3.getStateCode(), StateCodeContents.WAIT);

    }




} 

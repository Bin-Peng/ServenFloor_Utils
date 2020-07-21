package seven.utils.state.constants;

/**
 * ClassName: StateCodeContents <br/>
 * Description: 状态编码<br/>
 * date: 2020/7/21 10:30 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public interface StateCodeContents {
    /**
     * 申请中
     */
    String PENDING = "pending";
    /**
     * 通过
     */
    String PASSED = "passed";
    /**
     * 拒绝
     */
    String REFUSED = "refused";

    /**
     * 延迟审批
     */
    String WAIT = "wait";

}

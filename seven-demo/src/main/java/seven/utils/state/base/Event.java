package seven.utils.state.base;

import java.util.Map;

/**
 * ClassName: Event <br/>
 * Description: 事件<br/>
 * date: 2020/7/20 20:36 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public class Event {
    /**
     * 事件标识
     */
    private String eventCode;

    /**
     * 附属业务参数
     */
    private Map<Object, Object> attributes = null;

    public Event(String eventCode) {
        this.eventCode = eventCode;
    }

    public Event(String eventCode, Map<Object, Object> attributes) {
        this.eventCode = eventCode;
        this.attributes = attributes;
    }


    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public Map<Object, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<Object, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventCode='" + eventCode + '\'' +
                '}';
    }
}

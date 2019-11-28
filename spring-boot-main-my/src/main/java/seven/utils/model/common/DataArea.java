package seven.utils.model.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据区
 * Created by moche_000 on 2019/8/15.
 */
public class DataArea {
    /**
     * 输入区
     */
    private Map<String, Object> input;

    /**
     * 属性区
     */
    private Map<String, Object> properties;

    /**
     * 交易缓存区
     */
    private Map<String, Object> caches;

    /**
     * 输出区
     */
    private Map<String, Object> output;


    public void initData(int initialCapacity) {
        if (input == null) {
            input = new HashMap<String, Object>(initialCapacity);
        }
        if (properties == null) {
            properties = new HashMap<String, Object>(initialCapacity);
        }
        if (caches == null) {
            caches = new HashMap<String, Object>(initialCapacity);
        }
        if (output == null) {
            output = new HashMap<String, Object>(initialCapacity);
        }
    }

    public void initData() {
        if (input == null) {
            input = new HashMap<String, Object>();
        }
        if (properties == null) {
            properties = new HashMap<String, Object>();
        }
        if (caches == null) {
            caches = new HashMap<String, Object>();
        }
        if (output == null) {
            output = new HashMap<String, Object>();
        }
    }

    public Map<String, Object> getInput() {
        return input;
    }

    public void setInput(Map<String, Object> input) {
        this.input = input;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Map<String, Object> getCaches() {
        return caches;
    }

    public void setCaches(Map<String, Object> caches) {
        this.caches = caches;
    }

    public Map<String, Object> getOutput() {
        return output;
    }

    public void setOutput(Map<String, Object> output) {
        this.output = output;
    }
}

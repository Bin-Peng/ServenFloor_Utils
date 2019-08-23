package seven.utils.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;

/**
 * Created by moche_000 on 2019/8/23.
 */
public class KafkaTemplateSample {
    /**
     * 按照bean name装配
     * 如果不指定name，则按照属性名称作为bean name寻找实例
     * 再不中，按照类型装配
     * */
    @Resource
    private KafkaTemplate<String, byte[]> bytesKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Object> defaultKafkaTemplate;


//    public void byteSend(Object){
//
//    }

}

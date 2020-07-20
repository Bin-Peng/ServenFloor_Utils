//package seven.utils.sample;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.util.Assert;
//import seven.utils.config.KafkaConfiguration;
//
//import javax.annotation.Resource;
//
///**
// * Created by moche_000 on 2019/8/23.
// */
//public class KafkaTemplateSample {
//    /**
//     * 按照bean name装配
//     * 如果不指定name，则按照属性名称作为bean name寻找实例
//     * 再不中，按照类型装配
//     * */
//    @Resource()
//    private KafkaTemplate<String, byte[]> bytesKafkaTemplate;
//
//    @Resource(name = KafkaConfiguration.BYTE_KAFKA_TEMPLATE)
//    private KafkaTemplate<String, byte[]> bytesKafkaTemplateByName;
//
//    @Autowired
//    private KafkaTemplate<String, Object> defaultKafkaTemplate;
//
//    private String topicName;
//    private String key;
//
//    public void byteSend(byte[] request){
//        Assert.notNull(request, "参数值不能为空");
//        bytesKafkaTemplate.send(topicName, key, request);
//    }
//
//    public void objectSend(Object object){
//        Assert.notNull(object, "参数值不能为空");
//        defaultKafkaTemplate.send(topicName, key, object);
//    }
//
//}

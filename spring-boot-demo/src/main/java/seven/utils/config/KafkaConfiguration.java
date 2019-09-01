package seven.utils.config;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

/**
 * Kafka配置类
 * Created by moche_000 on 2019/8/23.
 */
@Configuration
@EnableKafka
@ConditionalOnProperty(name = "seven.utils.enable", matchIfMissing = true)
public class KafkaConfiguration {

    private final KafkaProperties properties;

    public KafkaConfiguration(KafkaProperties properties) {
        this.properties = properties;
    }

    /**
     * 基于字节序列化的消费工厂
     */
    public static final String BYTE_KAFKA_CONSUMER_FACTORY = "bytesKafkaConsumerFactory";
    /**
     * 基于字节序列化的生产工厂
     */
    public static final String BYTE_KAFKA_PRODUCER_FACTORY = "bytesKafkaProducerFactory";
    /**
     * 基于字节序列化的消息发送模板
     */
    public static final String BYTE_KAFKA_TEMPLATE = "bytesKafkaTemplate";

    /**
     * 基于字节序列化的消息消费监听器
     */
    public static final String BYTE_KAFKA_LISTENER_CONTAINER_FACTORY = "byteKafkaListenerContainerFactory";


    /**
     * 默认json序列化
     * @return
     */
    @Bean
    public ConsumerFactory<?,?> defaultKafkaConsumerFactory(){
        DefaultKafkaConsumerFactory<String, JsonNode> factory = new DefaultKafkaConsumerFactory<String, JsonNode>(this.properties.buildConsumerProperties());
        factory.setKeyDeserializer(new StringDeserializer());
        factory.setValueDeserializer(new JsonDeserializer(Object.class));
        return factory;
    }

    @Bean
    public ProducerFactory<?,?> defaultKafkaProducerFactory(){
        DefaultKafkaProducerFactory<String, JsonNode> factory = new DefaultKafkaProducerFactory<String, JsonNode>(this.properties.buildProducerProperties());
        factory.setKeySerializer(new StringSerializer());
        factory.setValueSerializer(new JsonSerializer());
        return factory;
    }

    @Bean(BYTE_KAFKA_PRODUCER_FACTORY)
    public ProducerFactory<?, ?> byteKafkaProducerFactory() {
        DefaultKafkaProducerFactory<String, byte[]> factory = new DefaultKafkaProducerFactory<String, byte[]>(this.properties.buildProducerProperties());
        factory.setKeySerializer(new StringSerializer());
        factory.setValueSerializer(new ByteArraySerializer());
        return factory;
    }

    @Bean(BYTE_KAFKA_CONSUMER_FACTORY)
    public ConsumerFactory<?, ?> byteKafkaConsumerFactory() {
        DefaultKafkaConsumerFactory<String, byte[]> factory = new DefaultKafkaConsumerFactory<String, byte[]>(this.properties.buildConsumerProperties());
        factory.setKeyDeserializer(new StringDeserializer());
        factory.setValueDeserializer(new ByteArrayDeserializer());
        return factory;
    }




    /**
     *

     * @param kafkaProducerFactory  通过 defaultKafkaProducerFactory() 方法构造注入
     * @param kafkaProducerListener
     * @return
     */
    @Bean(BYTE_KAFKA_TEMPLATE)
    public KafkaTemplate<?, ?> defaultKafkaTemplate(ProducerFactory<Object, Object> kafkaProducerFactory, ProducerListener<Object, Object> kafkaProducerListener) {
        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<Object, Object>(kafkaProducerFactory);
        kafkaTemplate.setProducerListener(kafkaProducerListener);
        kafkaTemplate.setDefaultTopic(this.properties.getTemplate().getDefaultTopic());
        return kafkaTemplate;
    }

    /**
     *
     * @param kafkaProducerFactory  通过 byteKafkaProducerFactory() 方法构造注入
     * @param kafkaProducerListener
     * @return
     */
    @Bean(BYTE_KAFKA_TEMPLATE)
    public KafkaTemplate<?, ?> byteKafkaTemplate(@Qualifier(BYTE_KAFKA_PRODUCER_FACTORY) ProducerFactory<Object, Object> kafkaProducerFactory, ProducerListener<Object, Object> kafkaProducerListener) {
        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<Object, Object>(kafkaProducerFactory);
        kafkaTemplate.setProducerListener(kafkaProducerListener);
        kafkaTemplate.setDefaultTopic(this.properties.getTemplate().getDefaultTopic());
        return kafkaTemplate;
    }


    /**
     * 默认监听配置
     * @param configurer
     * @param kafkaConsumerFactory
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<?,?> defaultKafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer, ConsumerFactory<Object, Object> kafkaConsumerFactory){
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        return factory;
    }

    /**
     * 字节监听配置
     * @param configurer
     * @param kafkaConsumerFactory
     * @return
     */
    @Bean(BYTE_KAFKA_LISTENER_CONTAINER_FACTORY)
    public ConcurrentKafkaListenerContainerFactory<?,?> byteKafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer, @Qualifier(BYTE_KAFKA_CONSUMER_FACTORY) ConsumerFactory<Object, Object> kafkaConsumerFactory){
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);
        return factory;
    }




}

package com.tcc.message.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitConfig {

    //https://blog.csdn.net/qq_38455201/article/details/80308771  这篇博客很详细
    private final Logger logger = LoggerFactory.getLogger(RabbitConfig.class);
    //直连交换机
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    //发布订阅模式 发布
    public static final String EXCHANGE_PUBLISH_SUBSCRIBE = "exchange_publish_subscribe";
    public static final String EXCHANGE_C = "my-mq-exchange_C";


    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";

    @Value("${spring.rabbitmq.host}")
    private String RABBIT_HOST;
    @Value("${spring.rabbitmq.username}")
    private String RABBIT_USERNAME;
    @Value("${spring.rabbitmq.password}")
    private String RABBIT_PASSWORD;
    @Value("${spring.rabbitmq.port}")
    private Integer RABBIT_PORT;

    @Bean
    public ConnectionFactory connectionFactory() {
        logger.info("Rabbit IOC Start==========");
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(RABBIT_HOST);
        connectionFactory.setUsername(RABBIT_USERNAME);
        connectionFactory.setPassword(RABBIT_PASSWORD);
        connectionFactory.setPort(RABBIT_PORT);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }


    /**
     * 创建A队列
     */
    @Bean
    public Queue queueA() {
        logger.info("queueA 队列创建成功===>");
        return new Queue(QUEUE_A, true); //队列持久
    }

    /**
     * 创建B队列
     */
    @Bean
    public Queue queueB() {
        logger.info("queueB 队列创建成功===>");
        return new Queue(QUEUE_B, true); //队列持久
    }

    /**
     * 创建C队列
     */
    @Bean
    public Queue queueC() {
        logger.info("queueC 队列创建成功===>");
        return new Queue(QUEUE_C, true); //队列持久
    }


    /**
     * 交换机 直连交换机 DirectExchange
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    /**
     * 交换机 主题交换机 TopicExchange
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_PUBLISH_SUBSCRIBE);
    }

    /**
     * 功能描述: <br>
     * <绑定交换机>
     * @Param: []
     * @Return: org.springframework.amqp.core.Binding
     * @Author: conlon
     * @Date: 2019/6/3 17:50
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_A);
    }

}

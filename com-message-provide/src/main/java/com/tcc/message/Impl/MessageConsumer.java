package com.tcc.message.Impl;

import com.tcc.message.config.RabbitConfig;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    /**
     * 功能描述: <br>
     * <单消费者模式>
     * @Param: [hello]
     * @Return: void
     * @Author: conlon
     * @Date: 2019/6/3 14:15
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_A)
    @RabbitHandler
    public void processOne(String hello) {
        System.out.println("ReceiverOne  : " + hello);
    }

    /**
     * 功能描述: <br>
     * <queues 相同时 - 单发布者 - 多消费者 模式> 且消费关系是负载均衡的
     * @Param: [hello]
     * @Return: void
     * @Author: conlon
     * @Date: 2019/6/3 14:15
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_A)
    @RabbitHandler
    public void processTow(String hello) {
        System.out.println("ReceiverTow  : " + hello);
    }

}
package com.tcc.message.Impl;

import com.tcc.facede.RabbitFacede;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitServiceImpl implements RabbitFacede {

    private final Logger logger = LoggerFactory.getLogger(RabbitServiceImpl.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 功能描述: <br>
     * <生产者-消费者模型 发送消息>
     *
     * @Param: [message, queueName]
     * @Return: void
     * @Author: conlon
     * @Date: 2019/5/31 14:10
     */
    @Override
    public void sendProducerMessage(String message, String queueName) {
        this.rabbitTemplate.convertAndSend(queueName, message);
    }

}

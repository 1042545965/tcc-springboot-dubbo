package com.tcc.facede;

public interface RabbitFacede {
    /**
     * 功能描述: <br>
     * <生产者-消费者模型 发送消息>
     * @Param: [message, queueName]
     * @Return: void
     * @Author: conlon
     * @Date: 2019/5/31 14:10
     */
    void sendProducerMessage(String message, String queueName);

}

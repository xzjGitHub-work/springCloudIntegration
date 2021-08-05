package com.tech.ecloudes.xzj.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 发送mq失败的回调函数
 */
public class MyConfirmCallback implements RabbitTemplate.ConfirmCallback {

    /**
     * 当消息发送失败会回调这个方法
     * @param correlationData
     * @param ack
     * @param s
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
//        log.info("CorrelationData content : " + correlationData);
//        log.info("Ack status : " + ack);
//        log.info("Cause content : " + cause);
//        if(ack){
//            log.info("消息成功发送，订单入库，更改订单状态");
//        }else{
//            log.info("消息发送失败："+correlationData+", 出现异常："+cause);
//        }

    }
}

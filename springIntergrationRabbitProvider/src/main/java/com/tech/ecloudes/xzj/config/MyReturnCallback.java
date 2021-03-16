package com.tech.ecloudes.xzj.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @describe:MQ失败回调方法
 *
 * @author:xzj
 * @createDate:2021/3/16 16:20
 * @param:
 * @return:
 */
@Component
public class MyReturnCallback implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText,
                                String exchange, String routingKey) {
        System.out.println("MyReturnCallback 1");
        System.out.println(message);
        System.out.println(replyCode);
        System.out.println(replyText);
        System.out.println(exchange);
        System.out.println(routingKey);
    }
}
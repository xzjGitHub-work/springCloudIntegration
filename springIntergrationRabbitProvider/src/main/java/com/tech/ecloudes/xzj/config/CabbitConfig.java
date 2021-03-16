package com.tech.ecloudes.xzj.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 设置
 * @Author: xzj
 * @CreateDate: 2021/3/16 16:19
 */
@Configuration
public class CabbitConfig {
    @Bean
    //@Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        //注意 这个ConnectionFactory 是使用javaconfig方式配置连接的时候才需要传入的
        //如果是yml配置的连接的话是不需要的
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //添加发送方确认模式方法
        //rabbitTemplate.setConfirmCallback(new MyConfirmCallback());
        //开启mandatory模式（开启失败回调）
        rabbitTemplate.setMandatory(true);
        //添加失败回调方法
        rabbitTemplate.setReturnCallback(new MyReturnCallback());
        return  rabbitTemplate;
    }
}

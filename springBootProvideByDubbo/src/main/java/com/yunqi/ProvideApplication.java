package com.yunqi;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: 提供方启动类
 * @Author: xzj
 * @CreateDate: 2021/1/14 11:13
 */

@SpringBootApplication //Springboot启动注解
@EnableDubbo
public class ProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProvideApplication.class,args);
    }
}

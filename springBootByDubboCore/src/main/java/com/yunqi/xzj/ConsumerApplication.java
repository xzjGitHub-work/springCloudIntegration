package com.yunqi.xzj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @describe:消费方启动
 *
 * @author:xzj
 * @createDate:2021/1/01 16:41
 * @param:
 * @return:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients //开启feign功能
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }
}

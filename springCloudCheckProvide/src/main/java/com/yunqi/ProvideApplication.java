package com.yunqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 提供方启动类
 * @Author: xzj
 * @CreateDate: 2021/1/14 11:13
 */

//@SpringBootApplication //Springboot启动注解
//@EnableEurekaClient //开启Eureka客户端注解
//@EnableCircuitBreaker //开启熔断器
//有一个组合注解@SpringCloudApplication 点开这个注解
@SpringCloudApplication
@MapperScan("com.yunqi.dao")
public class ProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProvideApplication.class,args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

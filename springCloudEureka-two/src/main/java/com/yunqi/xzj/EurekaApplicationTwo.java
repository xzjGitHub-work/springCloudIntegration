package com.yunqi.xzj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/1/14 13:54
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplicationTwo {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplicationTwo.class,args);
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

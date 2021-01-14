package com.yunqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: 提供方启动类
 * @Author: xzj
 * @CreateDate: 2021/1/14 11:13
 */

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.yunqi.dao")
public class ProvideApplicationTwo {

    public static void main(String[] args) {
        SpringApplication.run(ProvideApplicationTwo.class,args);
    }
}

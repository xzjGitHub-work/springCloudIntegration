package com.yunqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/1/14 11:13
 */

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.yunqi.dao")
public class ProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProvideApplication.class,args);
    }
}

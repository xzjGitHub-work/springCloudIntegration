package com.ecloude;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/4/27 16:00
 */
@EnableDubbo
@SpringBootApplication
public class ApplicationSpringBootStart {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationSpringBootStart.class,args);
    }
}

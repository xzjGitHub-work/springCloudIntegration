package com.ecloude;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/4/27 15:54
 */
@SpringBootApplication
@EnableDubbo
public class ApplicationSpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationSpringBoot.class, args);
    }
}

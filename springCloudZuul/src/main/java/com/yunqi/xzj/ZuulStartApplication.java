package com.yunqi.xzj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/1/18 13:40
 */
@SpringBootApplication
@EnableZuulProxy // 开启Zuul的网关功能,过滤功能比@EnableZuulServer多
@EnableEurekaClient
public class ZuulStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulStartApplication.class, args);
    }

}

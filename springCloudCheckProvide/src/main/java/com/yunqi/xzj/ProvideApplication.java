package com.yunqi.xzj;

import org.springframework.boot.SpringApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;

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
@ImportResource("classpath:test.xml")
public class ProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProvideApplication.class,args);
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                dispatcherServlet);
        registration.addUrlMappings("*.html");
        return registration;
    }
}

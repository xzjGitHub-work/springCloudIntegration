package com.xzj.yunqi;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/1/28 9:37
 */
@SpringBootApplication
//@NacosPropertySource(dataId = "nocos-server",groupId = "REGISTE_GROUP",autoRefreshed = true)
public class RegisterToNacosStart {
    public static void main(String[] args) {
        SpringApplication.run(RegisterToNacosStart.class,args);
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

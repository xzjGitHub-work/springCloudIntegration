package com.yunqi.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalanceConfiguration {

//    @Bean
//    public IRule rule() {
//        return new RandomRule();
//    }
}
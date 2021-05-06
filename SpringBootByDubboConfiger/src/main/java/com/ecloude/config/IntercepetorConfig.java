package com.ecloude.config;

import com.ecloude.intercepter.InterceptorByDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/4/29 14:30
 */
@Configuration
public class IntercepetorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private InterceptorByDubbo interceptorByDubbo;


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorByDubbo).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}

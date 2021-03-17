package com.tech.ecloudes.xzj.config;

import com.tech.ecloudes.xzj.intercapeter.RequestIntercepte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/17 10:29
 */
@Configuration
public class IntercepetConfig extends WebMvcConfigurationSupport {
    @Autowired
    private RequestIntercepte requestIntercepter;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestIntercepter).addPathPatterns("/rabbit/**");
        super.addInterceptors(registry);
    }
}

package com.tech.eclouds.xzj.configuration;

import com.tech.eclouds.xzj.interceptor.MyselfInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/15 8:50
 */
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private MyselfInterceptor interceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
        .addPathPatterns("/first/**");
        super.addInterceptors(registry);
    }
}

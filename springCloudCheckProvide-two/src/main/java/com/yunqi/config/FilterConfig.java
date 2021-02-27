package com.yunqi.config;

import com.yunqi.filter.MyFilter;
import com.yunqi.filter.MyFilterTwo;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;

/**
 * java类作用描述
 *
 * @author xuzhaoju
 * @createDate 2021/2/23 19:55
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean registrationProjectFilter(){
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(this.getFilter());
        filter.setOrder(1);
        filter.setName("111");
        filter.addUrlPatterns("/*");
        return filter;
    }
    @Bean
    public Filter getFilter(){
        return new MyFilter();
    }

    @Bean
    public FilterRegistrationBean registrationProjectFilterTwo(){
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(this.getFilterTwo());
        filter.setOrder(2);
        filter.setName("2222");
        filter.addUrlPatterns("/*");
        return filter;
    }
    @Bean
    public Filter getFilterTwo(){
        return new MyFilterTwo();
    }
}

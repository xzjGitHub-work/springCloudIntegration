package com.yunqi.xzj.filter;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * java类作用描述
 *
 * @author xuzhaoju
 * @createDate 2021/2/23 19:26
 */
//@WebFilter(urlPatterns = "/*")

//@Component
public class MyFilterTwo implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("执行了2");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("2过滤开始");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("2过滤结束");

    }

    @Override
    public void destroy() {
        System.out.println("销毁2");
    }
}

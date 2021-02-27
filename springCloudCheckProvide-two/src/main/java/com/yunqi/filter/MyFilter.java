package com.yunqi.filter;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * java类作用描述
 *
 * @author xuzhaoju
 * @createDate 2021/2/23 19:26
 */

//@Order(Integer.MAX_VALUE)
//@Component
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("执行了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        System.out.println(req.getRequestURL());
        System.out.println("1过滤开始");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("1过滤结束");
    }

    @Override
    public void destroy() {
        System.out.println("销毁");
    }
}

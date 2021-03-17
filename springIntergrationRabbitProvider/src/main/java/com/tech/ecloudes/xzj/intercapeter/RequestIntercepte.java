package com.tech.ecloudes.xzj.intercapeter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/17 10:23
 */
@Component
public class RequestIntercepte implements HandlerInterceptor {
    public static  ThreadLocal<String> token = new ThreadLocal<>();
    public static  ThreadLocal<String> time = new ThreadLocal<>();
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器");
        token.set(request.getParameter("token"));
        time.set(request.getParameter("time"));
        if (!StringUtils.isEmpty(request.getParameter("token"))){
            return true;
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        token.remove();
    }
}

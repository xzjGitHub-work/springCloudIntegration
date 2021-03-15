package com.tech.eclouds.xzj.interceptor;

import org.omg.PortableInterceptor.Interceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/15 8:38
 */
@Component
public class MyselfInterceptor implements HandlerInterceptor {

    public static ThreadLocal<String> APP_KEY = new ThreadLocal<String>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (StringUtils.isEmpty(request.getParameter("id"))){
            response.getWriter().write("参数不能为空");
//            response.setContentType( );
            APP_KEY.set("参数错误");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

package com.ecloude.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/4/29 14:28
 */
@Component
@Slf4j
public class InterceptorByDubbo implements HandlerInterceptor {
    public static ThreadLocal threadLocal = new ThreadLocal();
    Long start;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        start = System.currentTimeMillis();
        threadLocal.set(UUID.randomUUID().toString());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        threadLocal.remove();
        log.info("接口[ {} ] 耗时 [ {} ]",request.getRequestURI(),System.currentTimeMillis() - start);
    }
}

package com.tech.ecloudes.xzj.annotication;

import com.tech.ecloudes.xzj.intercapeter.RequestIntercepte;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/17 10:13
 */
@Aspect
@Component
public class MyselfAnnotationsImpl {

    @Pointcut("@annotation(com.tech.ecloudes.xzj.annotication.MyselfAnnotations)")
    public void MyselfAnnotationsImplPointCut() {

    }

    @Around("MyselfAnnotationsImplPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("执行了");
        Object proceed = null;
        if (!StringUtils.isEmpty(RequestIntercepte.token)) {
            proceed = point.proceed();
        }
        return proceed;
    }
}

package com.tech.ecloudes.xzj.annotication;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/17 11:25
 */
@Component
@Aspect
public class MyselfAnnotationsByRePlayImpl {
    @Pointcut("@annotation(com.tech.ecloudes.xzj.annotication.MyselfAnnotationsByRePlay)")
    public void MyselfAnnotationsByRePlayImpl(){
    }
    @Around("MyselfAnnotationsByRePlayImpl()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        String name = proceedingJoinPoint.getTarget().getClass().getPackage().toString().replace("package ", "") + "." + method.getName();
        System.out.println(name);
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }
}

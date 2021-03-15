package com.tech.eclouds.xzj.controller;

import com.tech.eclouds.xzj.interceptor.MyselfInterceptor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/15 8:40
 */
@RestController
@RequestMapping("/first")
public class FirstController {

    @RequestMapping("/firstWebInterface")
    public String myFirstWebInterface(String id){
        System.err.println("first访问到了");
        if (StringUtils.isEmpty(MyselfInterceptor.APP_KEY.get())){
            return "访问错误";
        }
        return "访问到了";
    }
}

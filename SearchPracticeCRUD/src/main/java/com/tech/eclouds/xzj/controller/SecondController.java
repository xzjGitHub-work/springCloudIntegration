package com.tech.eclouds.xzj.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/15 8:53
 */
@RestController
@RequestMapping("/secondController")
public class SecondController {

    @RequestMapping
    public String SecondController(String id){
        if (StringUtils.isEmpty(id)){
            RuntimeException runtimeException = new RuntimeException();
            runtimeException.printStackTrace();
            throw runtimeException;
        }
        System.err.println("second访问到了");
        return "访问到了";
    }
}

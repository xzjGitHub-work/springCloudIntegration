package com.yunqi.xzj.controller;

import com.yunqi.xzj.xzjTwo.servcice.RemoteHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demoController {
    @Autowired
    private RemoteHelloService remoteHelloService;


    @RequestMapping("/hello")
    public String Hello() {
        return remoteHelloService.hello();
    }

}
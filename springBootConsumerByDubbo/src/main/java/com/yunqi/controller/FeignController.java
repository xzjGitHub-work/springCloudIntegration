package com.yunqi.controller;

import com.yunqi.servcice.RemoteHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
    @Reference
    RemoteHelloService remoteHelloService;
//
    @RequestMapping("feignTest")
    public String feignTest() {
        String result = remoteHelloService.hello();
        if (result == null) {
            result = "出错";
        }
        return result;
    }

}
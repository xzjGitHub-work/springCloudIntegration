package com.yunqi.xzj.controller;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunqi.xzj.feignClien.ProvideClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/1/18 10:52
 */
@RestController
@RequestMapping("/consumerByFeign")
public class ConsumerControllerByFeign {

    @Autowired
    private ProvideClient provideClient;

    @GetMapping("/findList")
    @HystrixCommand(fallbackMethod = "findListFallBack")
    public JSONObject findList(String id) throws Exception {
        if (id.isEmpty() || "id".equals(id)){
            throw new Exception();
        }
        JSONObject json = provideClient.findList();
        return json;
    }
    public JSONObject findListFallBack(String id){
        JSONObject json  = new JSONObject();
        json.put("msg","使用feign调用启动熔断器");
        return json;
    }

}










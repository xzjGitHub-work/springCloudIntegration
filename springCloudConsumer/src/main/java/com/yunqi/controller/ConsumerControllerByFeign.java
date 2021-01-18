package com.yunqi.controller;

import com.alibaba.fastjson.JSONObject;
import com.yunqi.bean.UserModel;
import com.yunqi.feignClien.ProvideClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/1/18 10:52
 */
@RestController
@RequestMapping("/consumerByFeign")
public class ConsumerControllerByFeign {

    @Qualifier("provide-server")
    @Autowired
    private ProvideClient provideClient;

    @GetMapping("/findList")
    public JSONObject findList(){
        JSONObject json = provideClient.findList();
        return json;
    }
}










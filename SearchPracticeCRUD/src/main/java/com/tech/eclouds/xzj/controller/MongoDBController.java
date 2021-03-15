package com.tech.eclouds.xzj.controller;

import com.alibaba.fastjson.JSONObject;
import com.tech.eclouds.xzj.mongo.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/15 15:13
 */
@RestController
@RequestMapping("/mongoDBController")
public class MongoDBController {

    @Autowired
    private MongoService mongoService;

    @RequestMapping("/getDataByMongoDB")
    public JSONObject testMongoDB() {
        return mongoService.getUserDataByMongodb();
    }
}

package com.tech.eclouds.xzj.mongo.impl;

import com.alibaba.fastjson.JSONObject;
import com.tech.eclouds.xzj.domain.User;
import com.tech.eclouds.xzj.mongo.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/15 14:44
 */
@Service
public class MongoServiceImpl implements MongoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public JSONObject getUserDataByMongodb(){
        JSONObject jsonObject = new JSONObject();
        List<User> all = mongoTemplate.findAll(User.class);
        jsonObject.put("data",all);
        return jsonObject;
    }

}

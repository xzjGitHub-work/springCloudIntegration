package com.tech.eclouds.xzj.controller;

import com.alibaba.fastjson.JSONObject;
import com.tech.eclouds.xzj.mojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/18 16:38
 */
@RestController
@RequestMapping("/redis")
public class TestRedisCRUDController {
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/saveSet")
    public void saveString(){
        SetOperations<String,Object> opsForSet = redisTemplate.opsForSet();
        opsForSet.add("user1:user1",new User("许兆举","23","男"));
        opsForSet.add("user2:user2",new User("许兆举","23","男"));
        opsForSet.add("user3:user3",new User("许兆举","23","男"));
    }
    @RequestMapping("/getSet")
    public void getSet(){
        SetOperations<String,Object> opsForSet = redisTemplate.opsForSet();
        System.out.println(JSONObject.toJSONString(opsForSet.pop("user1")));
    }
}

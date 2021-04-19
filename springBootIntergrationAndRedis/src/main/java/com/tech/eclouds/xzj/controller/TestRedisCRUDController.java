package com.tech.eclouds.xzj.controller;

import com.alibaba.fastjson.JSONObject;
import com.tech.eclouds.xzj.mojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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
        System.out.println(JSONObject.toJSONString(opsForSet.pop("user1:user1")));
    }

    @RequestMapping("/saveList")
    public void saveList(){
        ListOperations opsForList = redisTemplate.opsForList();
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("许兆举","23","男"));
        list.add(new User("许兆举","232","男"));
        list.add(new User("许兆举","233","男"));
        ArrayList<String> listStr = new ArrayList<>();
        listStr.add("1");
        listStr.add("2");
        listStr.add("3");

        redisTemplate.opsForList().leftPush("xzjCreateList",10,listStr);
    }
    @RequestMapping("/getList")
    public void getList(){
        ListOperations opsForList = redisTemplate.opsForList();

        ArrayList<User> list = (ArrayList<User>)opsForList.getOperations().boundListOps("xzjCreateList");
        System.out.println(JSONObject.toJSONString(list));
    }

    @RequestMapping("/getHash")
    public void getHash(){
        HashOperations hash = redisTemplate.opsForHash();
        User user = (User)hash.get("xzjHash", "2222");
        System.out.println(JSONObject.toJSONString(user));
    }
    @RequestMapping("/setHash")
    public void setHash(){
        HashOperations hash = redisTemplate.opsForHash();
        hash.put("xzjHash","2222",new User("许兆举","233","男"));

    }
    @RequestMapping("/getZSetReverseRange")
    public void getZSetReverseRange(){
        String key = "key:reverse:range";
        ArrayList<String> listStr = new ArrayList<>();
        listStr.add("1");
        listStr.add("2");
        listStr.add("3");
        redisTemplate.boundListOps("listValue").rightPush(listStr);
        String str = (String) redisTemplate.boundListOps("listValue").index(1);
        System.err.println(str);
        System.out.println(JSONObject.toJSONString(redisTemplate.boundListOps("listValue").range(0,10)));
        Object[] objects = redisTemplate.boundZSetOps("listValue").reverseRange(0, 99).toArray();
        System.out.println(JSONObject.toJSONString(objects));
    }
}

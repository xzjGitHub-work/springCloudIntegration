package com.tech.eclouds.xzj;

import com.alibaba.fastjson.JSONObject;
import com.tech.eclouds.xzj.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/15 14:57
 */
@SpringBootTest
public class MongoTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testMongo(){
        List<User> all = mongoTemplate.findAll(User.class);
        System.out.println(JSONObject.toJSONString(all));

    }
}

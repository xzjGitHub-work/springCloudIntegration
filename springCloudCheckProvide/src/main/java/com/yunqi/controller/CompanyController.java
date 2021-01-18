package com.yunqi.controller;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunqi.bean.UserModel;
import com.yunqi.servcice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private UserService userService;

    /**
     * @describe:根据日志查询
     *
     * @author:xzj
     * @createDate:2021/1/14 16:42
     * @param:[id]
     * @return:com.yunqi.bean.UserModel
     */
    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "findOneFallBack")
    public JSONObject findOne(@PathVariable(name = "id")  String id) throws InterruptedException {
        System.out.println("提供方----one");
        Thread.sleep(3000L);
        JSONObject json = new JSONObject();
        json.put("data",userService.findOne(id));
        return json;
    }
    public JSONObject findOneFallBack(@PathVariable(name = "id")  String id){
        JSONObject json = new JSONObject();
        System.out.println("提供方----one");
        json.put("msg","当前服务正忙,请稍后再试");
        return json;

    }
}
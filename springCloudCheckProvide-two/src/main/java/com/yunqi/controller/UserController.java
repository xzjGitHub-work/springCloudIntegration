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
@RequestMapping("/provide")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @describe:根据日志查询
     * @author:xzj
     * @createDate:2021/1/14 16:42
     * @param:[id]
     * @return:com.yunqi.bean.UserModel
     */
    @GetMapping("/findOne")
    public JSONObject findOne(String id) {
        System.out.println("提供方----two");
        JSONObject json = new JSONObject();
        json.put("data", userService.findOne(id));
        json.put("msg", "success");
        return json;
    }

    public JSONObject findOneFallBack(String id) {
        JSONObject json = new JSONObject();
        System.out.println("提供方----one");
        json.put("msg", "当前服务正忙,请稍后再试");
        return json;

    }

    /**
     * @describe:查询所有的数据
     *
     * @author:xzj
     * @createDate:2021/1/18 13:51
     * @param:[]
     * @return:com.alibaba.fastjson.JSONObject
     */
    @GetMapping("/findList")
    public JSONObject findList(){
        JSONObject json = new JSONObject();
        json.put("data", userService.findList());
        return json;
    }

    /**
     * @describe:根据id查询数据
     *
     * @author:xzj
     * @createDate:2021/1/18 13:51
     * @param:[id]
     * @return:com.alibaba.fastjson.JSONObject
     */
    @GetMapping("/findById")
    public JSONObject findById(String id){
        JSONObject json = new JSONObject();
        json.put("data", userService.findOne(id));
        return json;
    }
}
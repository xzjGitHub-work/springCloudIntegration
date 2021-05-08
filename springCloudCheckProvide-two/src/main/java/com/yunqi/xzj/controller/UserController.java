package com.yunqi.xzj.controller;

import com.alibaba.fastjson.JSONObject;
import com.yunqi.xzj.server.servcice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
     * @author:xzj
     * @createDate:2021/1/18 13:51
     * @param:[]
     * @return:com.alibaba.fastjson.JSONObject
     */
    @GetMapping("/findList")
    public JSONObject findList() {
        JSONObject json = new JSONObject();
        json.put("data", userService.findList());
        return json;
    }

    /**
     * @describe:根据id查询数据
     * @author:xzj
     * @createDate:2021/1/18 13:51
     * @param:[id]
     * @return:com.alibaba.fastjson.JSONObject
     */
    @GetMapping("/findById")
    public JSONObject findById(String id) {
        JSONObject json = new JSONObject();
        json.put("data", userService.findOne(id));
        return json;
    }


    /**
     * 方法作用描述:测试过滤器
     *
     * @param map :
     * @return com.alibaba.fastjson.JSONObject
     * @author xzj
     * @createDate 2021/2/23 19:36
     */


    @GetMapping("/testFilter")
    public JSONObject testFilter(Map<String, String> map) {
        JSONObject json = new JSONObject();
        JSONObject.toJSONString(map);
        json.put("result", true);
        json.put("msg", "success");
        return json;
    }
}
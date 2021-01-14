package com.yunqi.controller;

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
    public UserModel findOne(@PathVariable(name = "id")  String id){
        System.out.println("提供方----two");
        return userService.findOne(id);
    }
}
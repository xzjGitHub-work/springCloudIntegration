package com.tech.ecloudes.xzj.controller;

import com.tech.ecloudes.xzj.annotication.MyselfAnnotations;
import com.tech.ecloudes.xzj.annotication.MyselfAnnotationsByRePlay;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/17 10:15
 */
@RestController
@RequestMapping("/rabbit")
public class TestAnnotations {
    String name;

    @RequestMapping("/testAnnotations")
    @MyselfAnnotations
    public String TestAnnotations() {
        name = "许兆举";
        System.out.println(name);
        return "success";
    }

    @RequestMapping("/testRePlayAnnotations")
    @MyselfAnnotationsByRePlay
    public String testRePlayAnnotations() {
        name = "许兆举";
        System.out.println(name);
        return "success";
    }
}

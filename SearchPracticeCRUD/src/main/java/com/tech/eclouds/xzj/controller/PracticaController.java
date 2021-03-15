package com.tech.eclouds.xzj.controller;

import com.alibaba.fastjson.JSONObject;
import com.tech.eclouds.xzj.service.PracticeByMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/15 9:33
 */
@RestController("/practice")
public class PracticaController {

    @Autowired
    private PracticeByMongoService practiceByMongoService;

    @RequestMapping
    public String save(HttpServletRequest request) {
        String name = request.getParameter("name");
        HashMap<String, String> map = new HashMap<>();
        map.put("name",request.getParameter("name"));
        practiceByMongoService.save(map);
        return "success";
    }
}

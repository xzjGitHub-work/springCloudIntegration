package com.tech.eclouds.xzj.configuration;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 异常统一管理配置
 * @Author: xzj
 * @CreateDate: 2021/3/15 8:54
 */
@Slf4j
@RestControllerAdvice
public class ExcepetionController {

    @ExceptionHandler(Exception.class)
    public JSONObject ExcepetionController(Exception e){
        e.printStackTrace();
        JSONObject json = new JSONObject();
        json.put("resule",false);
        json.put("msg","系统异常");
        return json;

    }
}

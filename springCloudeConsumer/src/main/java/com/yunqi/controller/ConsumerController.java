package com.yunqi.controller;

import com.yunqi.bean.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${test.url}")
    private String url;

    @GetMapping("/{id}")
    public UserModel findOne(@PathVariable(name = "id") String id) {
        url = url + id;
        return restTemplate.getForObject(url, UserModel.class);
    }
}
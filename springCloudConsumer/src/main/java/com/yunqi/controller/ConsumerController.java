package com.yunqi.controller;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yunqi.bean.UserModel;
import com.yunqi.servcice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Value("${test.url}")
    private String url;

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "findOneFallBack")
    public JSONObject findOne(@PathVariable(name = "id") String id) throws InterruptedException {
//        Thread.sleep(3000l);
//        System.out.println(userService.findOne("1"));
        List<ServiceInstance> instances = discoveryClient.getInstances("PROVICE-SERVER");
        ServiceInstance instance = instances.get(0);
//        url = url + id;
//        URI uri = instance.getUri();
//        return restTemplate.getForObject(url, UserModel.class);
        return restTemplate.getForObject("http://PROVICE-SERVER" + "/company/" + id, JSONObject.class);
    }

    public JSONObject findOneFallBack(@PathVariable(name = "id") String id) {
        JSONObject json = new JSONObject();
        json.put("msg","consumer-server服务正忙,请稍后再试");
        return json;
    }
}
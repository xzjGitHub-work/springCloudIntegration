package com.yunqi.controller;

import com.yunqi.bean.UserModel;
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
    @Value("${test.url}")
    private String url;

    @GetMapping("/{id}")
    public UserModel findOne(@PathVariable(name = "id") String id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("PROVICE-SERVER");
        ServiceInstance instance = instances.get(0);
//        url = url + id;
        URI uri = instance.getUri();
//        return restTemplate.getForObject(url, UserModel.class);
        return restTemplate.getForObject(instance.getUri() + "/company/"+id, UserModel.class);
    }
}
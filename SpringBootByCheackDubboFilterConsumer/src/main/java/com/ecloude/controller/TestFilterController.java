package com.ecloude.controller;

import com.ecloude.moniter.ZkMonitorClient;
import com.ecloude.moniter.ZkNodesWatcher;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import com.ecloude.service.TestFilterService;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/4/27 16:04
 */
@RestController
@Slf4j
public class TestFilterController {
    @Reference
    private TestFilterService testFilterService;


    @RequestMapping("/testFilterMapping")
    public String TestFilterMapping() throws InterruptedException {
        System.out.println(1111);
        Thread.sleep(5000l);
        System.out.println(testFilterService);
        return testFilterService.TestFilterMethod();
//        return "111";
    }
    @RequestMapping("/testFilterMappingAndToken")
    public String testFilterMappingAndToken(String token){
        return testFilterService.TestFilterMethodByToken(token);
//        return "111";
    }


    @Autowired
    private ZooKeeper zooKeeper;


    @RequestMapping("/getSurvive")
    public String getSurvive(){
        new ZkNodesWatcher(zooKeeper);
        return "true";
    }
}

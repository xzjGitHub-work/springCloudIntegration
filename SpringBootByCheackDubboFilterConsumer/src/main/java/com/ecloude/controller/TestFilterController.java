package com.ecloude.controller;

import org.apache.dubbo.config.annotation.Reference;
import com.ecloude.service.TestFilterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/4/27 16:04
 */
@RestController
public class TestFilterController {
    @Reference
    private TestFilterService testFilterService;

    @RequestMapping("/testFilterMapping")
    public String TestFilterMapping(){
        System.out.println(1111);
        System.out.println(testFilterService);
        return testFilterService.TestFilterMethod();
//        return "111";
    }
    @RequestMapping("/testFilterMappingAndToken")
    public String testFilterMappingAndToken(String token){
        return testFilterService.TestFilterMethodByToken(token);
//        return "111";
    }
}

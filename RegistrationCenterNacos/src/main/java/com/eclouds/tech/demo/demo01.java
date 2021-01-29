package com.eclouds.tech.demo;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;


import java.util.Properties;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/1/29 16:14
 */
public class demo01 {
    public static void main(String[] args) throws NacosException {
        //nacos 地址
        String serverAddr = "127.0.0.1:8848";
        //Data Id
        String dataId = "xzj-nacos-dataId";
        //Group
        String group = "xzj-nacos-group";
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        properties.put("namespace","public");
        ConfigService configService = NacosFactory.createConfigService(properties);
        //获取配置,String dataId, String group, long timeoutMs
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);

    }
}

package com.yunqi.service2.impl;

import com.yunqi.service2.RemoteHelloService2;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * java类作用描述
 *
 * @author xuzhaoju
 * @createDate 2021/2/28 18:44
 */
@Component
@Service
public class RemoteHelloServiceImpl2 implements RemoteHelloService2 {
    @Override
    public String hello() {
        return "provide";
    }
}

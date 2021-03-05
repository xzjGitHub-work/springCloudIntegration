package com.yunqi.xzj.server.servcice.impl;

import com.yunqi.xzj.server.servcice.RemoteHelloService;
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
public class RemoteHelloServiceImpl implements RemoteHelloService {
    @Override
    public String hello() {
        return "provide222222";
    }
}

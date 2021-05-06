package com.ecloude.service.impl;

import com.ecloude.service.TestFilterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/4/27 16:03
 */
@Service
@Component
@Slf4j
public class TestFilterServiceImpl implements TestFilterService {

    @Override
    public String TestFilterMethod() throws InterruptedException {
        Thread.sleep(5000l);
        log.info("provider: 打印[啊啊啊啊啊啊啊]");
        return "访问到了";
    }

    @Override
    public String TestFilterMethodByToken(String token) {
        return token;
    }
}

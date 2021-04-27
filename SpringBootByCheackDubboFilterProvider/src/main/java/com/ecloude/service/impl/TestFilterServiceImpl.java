package com.ecloude.service.impl;

import com.ecloude.service.TestFilterService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/4/27 16:03
 */
@Service
@Component
public class TestFilterServiceImpl implements TestFilterService {

    @Override
    public String TestFilterMethod() {
        return "访问到了";
    }

    @Override
    public String TestFilterMethodByToken(String token) {
        return token;
    }
}

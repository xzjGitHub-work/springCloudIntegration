package com.yunqi.feignClien;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/1/18 11:54
 */
@Component
public class ProvideClientFallBack implements ProvideClient {
    @Override
    public JSONObject findList() {
        JSONObject json = new JSONObject();
        json.put("data",null);
        json.put("msg","feign远程调用实现Hystrix熔断");
        return json;
    }
}

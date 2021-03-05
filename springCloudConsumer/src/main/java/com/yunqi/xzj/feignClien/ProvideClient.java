package com.yunqi.xzj.feignClien;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/1/18 10:53
 */
@FeignClient(value = "provide-server",fallback = ProvideClientFallBack.class)
public interface ProvideClient {

    @GetMapping("/provide/findList")
    JSONObject findList();

}

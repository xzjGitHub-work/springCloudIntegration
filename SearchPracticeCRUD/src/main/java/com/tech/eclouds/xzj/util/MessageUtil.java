package com.tech.eclouds.xzj.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author SunBo
 * @version v1.0
 * @since 2019/10/29
 */
public class MessageUtil {

    /**
     * 检测是否为空
     *
     * @param function 方法名
     * @param args 参数
     * @return 结果
     */
    public static String buildJSON(String function, Map<String, Object> args) {
        if (StringUtils.isEmpty(function)) {
            Assert.notNull(function);
        }
        JSONObject data = new JSONObject();
        data.put("function", function);
        if (MapUtils.isNotEmpty(args)) {
            data.put("args", args);
        }
        return data.toJSONString();
    }
}

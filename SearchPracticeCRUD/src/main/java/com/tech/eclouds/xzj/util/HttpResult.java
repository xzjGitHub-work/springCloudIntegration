package com.tech.eclouds.xzj.util;

import com.alibaba.fastjson.JSONObject;

public class HttpResult {

    // 响应的状态码
    private int code;

    // 响应的响应体
    private JSONObject body;

    public HttpResult(int code, JSONObject body) {
        this.code = code;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JSONObject getBody() {
        return body;
    }

    public void setBody(JSONObject body) {
        this.body = body;
    }
}
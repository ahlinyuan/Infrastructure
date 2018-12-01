package com.ahlinyuan.infrastructure.M.http;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class RequestParam {
    private Map<String, Object> map = new HashMap<>();

    public RequestParam() {
        //添加默认参数
        map.put("ts", System.currentTimeMillis());
    }

    public Map<String, Object> put(String key, Object value) {
        map.put(key, value);
        return map;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

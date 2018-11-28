package com.ahlinyuan.infrastructure.M.http;

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
}

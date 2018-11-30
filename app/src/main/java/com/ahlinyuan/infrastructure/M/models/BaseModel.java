package com.ahlinyuan.infrastructure.M.models;

import com.ahlinyuan.infrastructure.M.util.Enums;

/**
 * 基础的请求模型
 * Created by Administrator on 2018/6/25.
 */
public class BaseModel {

    //状态码
    private int code;
    //状态信息
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    //================================

    /**
     * 响应是否成功
     *
     * @return true 成功
     */
    public boolean isResponseSuccess() {
        return Enums.ResponseCode.SUCCESS.value == code;
    }
}

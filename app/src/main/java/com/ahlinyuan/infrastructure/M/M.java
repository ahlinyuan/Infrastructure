package com.ahlinyuan.infrastructure.M;

import com.ahlinyuan.infrastructure.M.http.Network;

/**
 * 数据层入口类
 * Created by ahlinyuan on 2018/11/30.
 */
public class M {

    /**
     * 获取网络请求引用
     *
     * @return 网络请求引用
     */
    public static Network getNetwork() {
        return Network.getInstance();
    }
}

package com.ahlinyuan.infrastructure;

import android.app.Application;
import android.content.Context;

import com.ahlinyuan.infrastructure.M.http.Network;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

public class IApplication extends Application {

    /**
     * 是否是线上模式
     */
    public static final boolean ISLINE = false;

    /**
     * APP上下文实例缓存
     */
    public static Context AppCtx;

    @Override
    public void onCreate() {
        super.onCreate();
        //缓存全局上下文对象
        AppCtx = getApplicationContext();
        //初始化屏幕适配
        ScreenAdapterTools.init(this);
        //初始化网络请求
        Network.init(null);
    }
}

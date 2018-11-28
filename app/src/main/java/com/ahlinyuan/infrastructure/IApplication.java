package com.ahlinyuan.infrastructure;

import android.app.Application;
import android.content.Context;

import com.ahlinyuan.infrastructure.M.http.HttpAPI;

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
        AppCtx = getApplicationContext();
        HttpAPI.init();
    }
}

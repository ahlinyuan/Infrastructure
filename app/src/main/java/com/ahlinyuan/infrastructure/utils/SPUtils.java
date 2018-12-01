package com.ahlinyuan.infrastructure.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static com.ahlinyuan.infrastructure.IApplication.AppCtx;

public class SPUtils {

    private static SharedPreferences SP;

    /**
     * 清空数据
     *
     * @return true 成功
     */
    public static boolean clear(String name) {
        SP = AppCtx.getSharedPreferences(AppCtx.getPackageName() + "." + name, Context.MODE_PRIVATE);
        return SP.edit().clear().commit();
    }

    /**
     * 保存数据
     *
     * @param key   key
     * @param value value
     */
    public static boolean save(String name, String key, String value) {
        SP = AppCtx.getSharedPreferences(AppCtx.getPackageName() + "." + name, Context.MODE_PRIVATE);
        return SP.edit().putString(key, value).commit();
    }

    /**
     * 获取保存的数据
     *
     * @param key      键
     * @param defValue 默认返回的value
     * @return value
     */
    public static String load(String name, String key, String defValue) {
        SP = AppCtx.getSharedPreferences(AppCtx.getPackageName() + "." + name, Context.MODE_PRIVATE);
        return SP.getString(key, defValue);
    }

    /**
     * 保存数据
     *
     * @param key   key
     * @param value value
     */
    public static boolean save(String name, String key, boolean value) {
        SP = AppCtx.getSharedPreferences(AppCtx.getPackageName() + "." + name, Context.MODE_PRIVATE);
        return SP.edit().putBoolean(key, value).commit();
    }

    /**
     * 获取保存的数据
     *
     * @param key      键
     * @param defValue 默认返回的value
     * @return value
     */
    public static boolean load(String name, String key, boolean defValue) {
        SP = AppCtx.getSharedPreferences(AppCtx.getPackageName() + "." + name, Context.MODE_PRIVATE);
        return SP.getBoolean(key, defValue);
    }

    /**
     * 保存数据
     *
     * @param key   key
     * @param value value
     */
    public static boolean save(String name, String key, int value) {
        SP = AppCtx.getSharedPreferences(AppCtx.getPackageName() + "." + name, Context.MODE_PRIVATE);
        return SP.edit().putInt(key, value).commit();
    }

    /**
     * 获取保存的数据
     *
     * @param key      键
     * @param defValue 默认返回的value
     * @return value
     */
    public static int load(String name, String key, int defValue) {
        SP = AppCtx.getSharedPreferences(AppCtx.getPackageName() + "." + name, Context.MODE_PRIVATE);
        return SP.getInt(key, defValue);
    }

    /**
     * 保存数据
     *
     * @param key   key
     * @param value value
     */
    public static boolean save(String name, String key, long value) {
        SP = AppCtx.getSharedPreferences(AppCtx.getPackageName() + "." + name, Context.MODE_PRIVATE);
        return SP.edit().putLong(key, value).commit();
    }

    /**
     * 获取保存的数据
     *
     * @param key      键
     * @param defValue 默认返回的value
     * @return value
     */
    public static long load(String name, String key, long defValue) {
        SP = AppCtx.getSharedPreferences(AppCtx.getPackageName() + "." + name, Context.MODE_PRIVATE);
        return SP.getLong(key, defValue);
    }
}

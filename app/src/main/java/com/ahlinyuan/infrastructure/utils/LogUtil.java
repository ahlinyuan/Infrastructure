package com.ahlinyuan.infrastructure.utils;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

import static com.ahlinyuan.infrastructure.IApplication.ISLINE;

/**
 * Log封装类
 * Created by Ahlin on 2018/11/21.
 */
public class LogUtil {
    private static final String TAG = "infrastructure";

    public static void i(String msg) {
        if (ISLINE) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (ISLINE) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (ISLINE) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String msg, Throwable ex) {
        if (ISLINE) {
            Log.e(TAG, msg + ":\n" + getExceptionString(ex));
        }
    }

    /**
     * 获取异常字符串
     *
     * @param ex 异常
     * @return 异常字符串
     */
    private static String getExceptionString(Throwable ex) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            return sw.toString();
        } catch (Exception e) {
            return "获取异常信息时发生异常";
        }
    }
}

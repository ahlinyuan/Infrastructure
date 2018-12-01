package com.ahlinyuan.infrastructure.V.utils;

import android.content.Context;
import android.text.TextUtils;

/**
 * Toast工具类
 * Created by owner on 2016/4/26.
 */
public class Toast {

    private static android.widget.Toast toast;

    /**
     * Toast
     *
     * @param context 上下文
     * @param res     资源ID
     */
    public static void show(Context context, int res) {
        if (context == null) return;
        show(context, context.getString(res));
    }

    /**
     * Toast
     *
     * @param context 上下文
     * @param text    文本
     */
    public static void show(Context context, CharSequence text) {
        if (context == null) return;
        if (TextUtils.isEmpty(text)) return; //空文本不显示

        if (toast == null)
            toast = android.widget.Toast.makeText(context, text, android.widget.Toast.LENGTH_SHORT);
        else
            toast.setText(text);
        toast.show();
    }
}
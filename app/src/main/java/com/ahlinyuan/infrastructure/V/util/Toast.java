package com.ahlinyuan.infrastructure.V.util;

import android.content.Context;
import android.text.TextUtils;

/**
 * Toast工具类
 * Created by owner on 2016/4/26.
 */
public class Toast {

    /**
     * Toast
     *
     * @param res 资源ID
     */
    public static void show(Context ctx, int res) {
        show(ctx, ctx.getString(res));
    }

    /**
     * Toast
     *
     * @param text 文本
     */
    public static void show(Context ctx, CharSequence text) {
        if (TextUtils.isEmpty(text)) //空文本不显示
            return;
        android.widget.Toast.makeText(ctx, text, android.widget.Toast.LENGTH_SHORT).show();
    }
}
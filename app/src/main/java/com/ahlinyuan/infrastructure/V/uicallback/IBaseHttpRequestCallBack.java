package com.ahlinyuan.infrastructure.V.uicallback;

import com.ahlinyuan.infrastructure.V.IBaseView;

public interface IBaseHttpRequestCallBack extends IBaseView {

    /**
     * 开始网络请求
     */
    void onHttpRequestStart();

    /**
     * 网络请求错误
     */
    void onHttpRequestError(int code, String error);

    /**
     * 完成网络请求
     */
    void onHttpRequestComplete();
}

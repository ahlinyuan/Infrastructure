package com.ahlinyuan.infrastructure.V;

/**
 * @author ahlinyuan
 * @date 2019/1/7
 * @description 网络请求回调接口
 */
public interface IHttpRequestCallbackView extends IBaseView {

    /**
     * 开始请求
     */
    void onHttpRequestStart();

    /**
     * 请求成功
     *
     * @param msg 成功提示
     */
    void onHttpRequestSuccess(String msg);

    /**
     * 请求失败
     *
     * @param code 状态码
     * @param mag  提示信息
     */
    void onHttpRequestError(int code, String mag);

    /**
     * 请求完成
     */
    void onHttpRequestComplete();
}

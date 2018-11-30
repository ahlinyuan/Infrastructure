package com.ahlinyuan.infrastructure.P;

import com.ahlinyuan.infrastructure.V.IBaseView;

/**
 * 基础控制器
 * Created by Administrator on 2018/6/25.
 */
public class BasePresenter<T extends IBaseView> implements IBasePresenter {

    /**
     * 网络异常错误码
     */
    public static final int CODE_NET_ERROR = -1;

    /**
     * UIView
     */
    protected T view;

    public BasePresenter(T view) {
        this.view = view;
    }
}

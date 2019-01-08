package com.ahlinyuan.infrastructure.P;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ahlinyuan.infrastructure.V.IBaseView;
import com.ahlinyuan.infrastructure.utils.LogUtils;

/**
 * @author ahlinyuan
 * @date 2019/1/7
 * @description 所有Presenter的基类，并不强制实现这些方法，有需要在重写
 */
public class BasePresenter<V extends IBaseView> {

    /**
     * 网络异常错误码
     */
    public static final int CODE_NET_ERROR = -1;


    /**
     * V层view
     */
    private V mView;

    /**
     * Presenter被创建后调用
     *
     * @param savedState 被意外销毁后重建后的Bundle
     */
    public void onCreatePersenter(@Nullable Bundle savedState) {
        LogUtils.e("P onCreatePersenter = ");
    }


    /**
     * 绑定View
     */
    public void onAttachView(V mvpView) {
        mView = mvpView;
        LogUtils.e("P onResume");
    }

    /**
     * 解除绑定View
     */
    public void onDetachView() {
        mView = null;
        LogUtils.e("P onDetachView = ");
    }

    /**
     * Presenter被销毁时调用
     */
    public void onDestroyPersenter() {
        LogUtils.e("P onDestroy = ");
    }

    /**
     * 在Presenter意外销毁的时候被调用，它的调用时机和Activity、Fragment、View中的onSaveInstanceState
     * 时机相同
     *
     * @param outState
     */
    public void onSaveInstanceState(Bundle outState) {
        LogUtils.e("P onSaveInstanceState = ");
    }

    /**
     * 获取V层接口View
     *
     * @return 返回当前View
     */
    public V getView() {
        return mView;
    }
}

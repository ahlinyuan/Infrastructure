package com.ahlinyuan.infrastructure.V.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahlinyuan.infrastructure.P.BasePresenter;
import com.ahlinyuan.infrastructure.P.factory.IPresenterFactory;
import com.ahlinyuan.infrastructure.P.factory.PresenterFactory;
import com.ahlinyuan.infrastructure.P.proxy.PresenterProxy;
import com.ahlinyuan.infrastructure.P.proxy.IPresenterProxy;
import com.ahlinyuan.infrastructure.V.activities.BaseActivity;
import com.ahlinyuan.infrastructure.V.IBaseView;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.ButterKnife;

/**
 * @author ahlinyuan
 * @date 2019/1/7
 * @description 基础fragment
 */
public abstract class BaseFragment<V extends IBaseView, P extends BasePresenter<V>> extends RxFragment implements IPresenterProxy<V, P> {

    /**
     * 调用onSaveInstanceState时存入Bundle的key
     */
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private PresenterProxy<V, P> mProxy = new PresenterProxy<>(PresenterFactory.createFactory(getClass()));


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutID(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState != null){
            mProxy.onRestoreInstanceState(savedInstanceState);
        }

        //适配分屏和UI
        ScreenAdapterTools.getInstance().reset(getContext());
        ScreenAdapterTools.getInstance().loadView(view);

        ButterKnife.bind(this, view);
        init(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mProxy.onResume((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProxy.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(PRESENTER_SAVE_KEY,mProxy.onSaveInstanceState());
    }

    /**
     * 可以实现自己PresenterMvpFactory工厂
     *
     * @param presenterFactory PresenterFactory类型
     */
    @Override
    public void setPresenterFactory(IPresenterFactory<V, P> presenterFactory) {
        mProxy.setPresenterFactory(presenterFactory);
    }

    /**
     * 获取创建Presenter的工厂
     *
     * @return PresenterMvpFactory类型
     */
    @Override
    public IPresenterFactory<V, P> getPresenterFactory() {
        return mProxy.getPresenterFactory();
    }

    /**
     * 获取Presenter
     * @return P
     */
    @Override
    public P getPresenter() {
        return mProxy.getPresenter();
    }

    /**
     * 获取Fragment布局ID
     *
     * @return 布局ID
     */
    protected abstract int getLayoutID();

    /**
     * 初始化完成，替代{@link #onViewCreated(View, Bundle)}
     */
    protected abstract void init(View view, Bundle savedInstanceState);



    //Toast====================================

    /**
     * Toast
     *
     * @param res 资源
     */
    public void toast(int res) {
        ((BaseActivity) getActivity()).toast(res);
    }

    /**
     * Toast
     *
     * @param text 文本
     */
    public void toast(CharSequence text) {
        ((BaseActivity) getActivity()).toast(text);
    }

    //进度框=============================

    /**
     * 显示进度框
     */
    public void showProgress() {
        ((BaseActivity) getActivity()).showProgress();
    }

    /**
     * 关闭进度框
     */
    public void dismissProgress() {
        ((BaseActivity) getActivity()).dismissProgress();
    }
}

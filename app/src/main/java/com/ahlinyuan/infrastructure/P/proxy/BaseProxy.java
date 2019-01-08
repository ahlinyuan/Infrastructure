package com.ahlinyuan.infrastructure.P.proxy;

import android.os.Bundle;

import com.ahlinyuan.infrastructure.P.BasePresenter;
import com.ahlinyuan.infrastructure.P.factory.PresenterFactory;
import com.ahlinyuan.infrastructure.V.IBaseView;
import com.ahlinyuan.infrastructure.utils.LogUtils;

/**
 * @author ahlinyuan
 * @date 2019/1/7
 * @description 代理实现类，用来管理Presenter的生命周期，还有和view之间的关联
 */
public class BaseProxy<V extends IBaseView, P extends BasePresenter<V>> implements IPresenterProxy<V, P> {

    /**
     * 获取onSaveInstanceState中bundle的key
     */
    private static final String PRESENTER_KEY = "presenter_key";

    /**
     * Presenter工厂类
     */
    private PresenterFactory<V, P> mFactory;
    private P mPresenter;
    private Bundle mBundle;
    private boolean mIsAttchView;

    public BaseProxy(PresenterFactory<V, P> presenterFactory) {
        this.mFactory = presenterFactory;
    }

    /**
     * 设置Presenter的工厂类,这个方法只能在创建Presenter之前调用,也就是调用getMvpPresenter()之前，如果Presenter已经创建则不能再修改
     *
     * @param presenterFactory PresenterFactory类型
     */
    @Override
    public void setPresenterFactory(PresenterFactory<V, P> presenterFactory) {
        if (mPresenter != null) {
            throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用，如果Presenter已经创建则不能再修改");
        }
        this.mFactory = presenterFactory;
    }

    /**
     * 获取Presenter的工厂类
     *
     * @return PresenterMvpFactory类型
     */
    @Override
    public PresenterFactory<V, P> getPresenterFactory() {
        return mFactory;
    }

    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     * 如果之前创建过，而且是以外销毁则从Bundle中恢复
     */
    @Override
    public P getPresenter() {
        LogUtils.e("Proxy getPresenter");
        if (mFactory != null) {
            if (mPresenter == null) {
                mPresenter = mFactory.createPresenter();
                mPresenter.onCreatePersenter(mBundle == null ? null : mBundle.getBundle(PRESENTER_KEY));
            }
        }
        LogUtils.e("Proxy getMvpPresenter = " + mPresenter);
        return mPresenter;
    }

    /**
     * 绑定Presenter和view
     *
     * @param mvpView view
     */
    public void onResume(V mvpView) {
        getPresenter();
        LogUtils.e("Proxy onResume");
        if (mPresenter != null && !mIsAttchView) {
            mPresenter.onAttachView(mvpView);
            mIsAttchView = true;
        }
    }

    /**
     * 销毁Presenter持有的View
     */
    private void onDetachMvpView() {
        LogUtils.e("Proxy onDetachMvpView = ");
        if (mPresenter != null && mIsAttchView) {
            mPresenter.onDetachView();
            mIsAttchView = false;
        }
    }

    /**
     * 销毁Presenter
     */
    public void onDestroy() {
        LogUtils.e("Proxy onDestroy = ");
        if (mPresenter != null) {
            onDetachMvpView();
            mPresenter.onDestroyPersenter();
            mPresenter = null;
        }
    }

    /**
     * 意外销毁的时候调用
     *
     * @return Bundle，存入回调给Presenter的Bundle和当前Presenter的id
     */
    public Bundle onSaveInstanceState() {
        LogUtils.e("Proxy onSaveInstanceState = ");
        Bundle bundle = new Bundle();
        getPresenter();
        if (mPresenter != null) {
            Bundle presenterBundle = new Bundle();
            //回调Presenter
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY, presenterBundle);
        }
        return bundle;
    }

    /**
     * 意外关闭恢复Presenter
     *
     * @param savedInstanceState 意外关闭时存储的Bundler
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        LogUtils.e("Proxy onRestoreInstanceState = ");
        LogUtils.e("Proxy onRestoreInstanceState Presenter = " + mPresenter);
        mBundle = savedInstanceState;
    }
}
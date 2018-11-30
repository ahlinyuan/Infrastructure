package com.ahlinyuan.infrastructure.V.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahlinyuan.infrastructure.V.activities.BaseActivity;
import com.ahlinyuan.infrastructure.V.IBaseView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import butterknife.ButterKnife;

/**
 * 基础Fragment
 * Created by Administrator on 2018/6/25.
 */
public abstract class BaseFragment extends RxFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutID(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //适配分屏和UI
        ScreenAdapterTools.getInstance().reset(getContext());
        ScreenAdapterTools.getInstance().loadView(view);

        ButterKnife.bind(this, view);
        init(view, savedInstanceState);
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

    //基础控制器的View================================

    protected class BasePresenterView implements IBaseView {
        @Override
        public <T> LifecycleTransformer<T> bindUntilDestroyEvent() {
            return bindUntilEvent(FragmentEvent.DESTROY);
        }
    }

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

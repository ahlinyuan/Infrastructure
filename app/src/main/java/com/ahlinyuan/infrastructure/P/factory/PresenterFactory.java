package com.ahlinyuan.infrastructure.P.factory;

import com.ahlinyuan.infrastructure.P.BasePresenter;
import com.ahlinyuan.infrastructure.V.IBaseView;

/**
 * @author ahlinyuan
 * @date 2019/1/7
 * @description Presenter工厂接口
 */
public interface PresenterFactory<V extends IBaseView,P extends BasePresenter<V>> {

    /**
     * 创建Presenter的接口方法
     * @return 需要创建的Presenter
     */
    P createPresenter();
}

package com.ahlinyuan.infrastructure.P.proxy;

import com.ahlinyuan.infrastructure.P.BasePresenter;
import com.ahlinyuan.infrastructure.P.factory.PresenterFactory;
import com.ahlinyuan.infrastructure.V.IBaseView;

/**
 * @author ahlinyuan
 * @date 2019/1/7
 * @description 代理接口
 */
public interface IPresenterProxy<V extends IBaseView, P extends BasePresenter<V>> {

    /**
     * 设置创建Presenter的工厂
     * @param presenterFactory PresenterFactory类型
     */
    void setPresenterFactory(PresenterFactory<V,P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     * @return 返回PresenterFactory类型
     */
    PresenterFactory<V,P> getPresenterFactory();


    /**
     * 获取创建的Presenter
     * @return 指定类型的Presenter
     */
    P getPresenter();

}

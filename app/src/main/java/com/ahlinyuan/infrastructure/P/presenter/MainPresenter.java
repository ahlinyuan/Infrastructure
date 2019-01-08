package com.ahlinyuan.infrastructure.P.presenter;

import android.annotation.SuppressLint;

import com.ahlinyuan.infrastructure.M.M;
import com.ahlinyuan.infrastructure.P.BasePresenter;
import com.ahlinyuan.infrastructure.V.activities.IMainView;

public class MainPresenter extends BasePresenter<IMainView> {

    @SuppressLint("CheckResult")
    public void requestStr() {
        getView().onHttpRequestStart();
        M.getNetwork().checkUpdate(1).compose(getView().bindUntilDestroyEvent()).subscribe(baseModel -> {
            if (baseModel.isResponseSuccess()) {
                getView().onHttpRequestSuccess(baseModel.getMsg());
            } else {
                getView().onHttpRequestError(baseModel.getCode(), baseModel.getMsg());
            }
        }, e -> {
            getView().onHttpRequestError(CODE_NET_ERROR, e.getMessage());
            getView().onHttpRequestComplete();
        });
    }
}

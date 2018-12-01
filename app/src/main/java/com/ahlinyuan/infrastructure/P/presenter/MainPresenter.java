package com.ahlinyuan.infrastructure.P.presenter;

import android.annotation.SuppressLint;

import com.ahlinyuan.infrastructure.M.M;
import com.ahlinyuan.infrastructure.P.BasePresenter;
import com.ahlinyuan.infrastructure.V.uicallback.IMainView;

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {


    public MainPresenter(IMainView view) {
        super(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void requestStr() {
        view.onHttpRequestStart();
        M.getNetwork().checkUpdate(1).compose(view.bindUntilDestroyEvent()).subscribe(baseModel -> {
            if (baseModel.isResponseSuccess()) {
                view.onHttpRequestSuccess(baseModel.getMsg());
            } else {
                view.onHttpRequestError(baseModel.getCode(), baseModel.getMsg());
            }
        }, e -> {
            view.onHttpRequestError(CODE_NET_ERROR, e.getMessage());
            view.onHttpRequestComplete();
        });
    }
}

package com.ahlinyuan.infrastructure.V.activities;

import android.os.Bundle;

import com.ahlinyuan.infrastructure.P.factory.CreatePresenter;
import com.ahlinyuan.infrastructure.P.presenter.MainPresenter;
import com.ahlinyuan.infrastructure.R;

@CreatePresenter(MainPresenter.class)
public class MainActivity extends BaseActivity<IMainView, MainPresenter> implements IMainView {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        findViewById(R.id.tv).setOnClickListener(v -> {
            getPresenter().requestStr();
        });
    }


    @Override
    public void onHttpRequestStart() {

    }

    @Override
    public void onHttpRequestSuccess(String msg) {

    }

    @Override
    public void onHttpRequestError(int code, String mag) {

    }

    @Override
    public void onHttpRequestComplete() {

    }
}
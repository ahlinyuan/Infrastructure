package com.ahlinyuan.infrastructure.P.presenter;

import com.ahlinyuan.infrastructure.P.BasePresenter;
import com.ahlinyuan.infrastructure.V.uicallback.IMainView;

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {


    public MainPresenter(IMainView view) {
        super(view);
    }

    @Override
    public void requestStr() {

    }
}

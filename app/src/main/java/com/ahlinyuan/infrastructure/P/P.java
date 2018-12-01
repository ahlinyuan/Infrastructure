package com.ahlinyuan.infrastructure.P;

import com.ahlinyuan.infrastructure.P.presenter.MainPresenter;
import com.ahlinyuan.infrastructure.V.uicallback.IMainView;

import io.reactivex.annotations.Nullable;

public class P {

    public static MainPresenter createMainPresenter(@Nullable IMainView view) {
        return new MainPresenter(view);
    }
}

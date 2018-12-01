package com.ahlinyuan.infrastructure.V.activities;

import android.os.Bundle;

import com.ahlinyuan.infrastructure.P.P;
import com.ahlinyuan.infrastructure.R;
import com.ahlinyuan.infrastructure.V.uicallback.IMainView;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        findViewById(R.id.tv).setOnClickListener(v -> {
//            showProgress();
//            toast("ahlinyuan");

            P.createMainPresenter(new MainView(true)).requestStr();
        });
    }

    public class MainView extends BaseHttpRequestCallBack implements IMainView {

        MainView() {
        }

        MainView(boolean isShowProgress) {
            super(isShowProgress);
        }

        @Override
        public void onHttpRequestSuccess(String str) {
            runOnUiThread(() -> toast(str));
        }
    }
}
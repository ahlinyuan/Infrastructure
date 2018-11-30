package com.ahlinyuan.infrastructure.V.activities;

import android.app.Activity;
import android.os.Bundle;

import com.ahlinyuan.infrastructure.R;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        findViewById(R.id.tv).setOnClickListener(v -> {
            showProgress();
        });
    }
}
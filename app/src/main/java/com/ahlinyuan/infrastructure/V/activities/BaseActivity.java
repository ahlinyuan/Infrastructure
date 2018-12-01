package com.ahlinyuan.infrastructure.V.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ahlinyuan.infrastructure.R;
import com.ahlinyuan.infrastructure.V.IBaseView;
import com.ahlinyuan.infrastructure.V.uicallback.IBaseHttpRequestCallBack;
import com.ahlinyuan.infrastructure.V.utils.Toast;
import com.ahlinyuan.infrastructure.utils.LogUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static com.ahlinyuan.infrastructure.IApplication.AppCtx;

/**
 * 基础页面
 * Created by Administrator on 2018/6/25.
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    //页面列表缓存
    private static final List<Activity> ACTIVITIES = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ACTIVITIES.add(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutID());

        //如果希望android7.0分屏也适配的话,加上这句
        ScreenAdapterTools.getInstance().reset(this);
        //在setContentView();后面加上适配语句
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());

        ButterKnife.bind(this);
        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ACTIVITIES.remove(this);
    }

    /**
     * 退出所有页面
     */
    protected void exit() {
        for (Activity act : ACTIVITIES) {
            act.finish();
        }
    }

    /**
     * 弹出到某个页面
     *
     * @param clz 页面的clz
     */
    protected void popToActivity(Class clz) {
        //判断是否存在页面
        boolean isExist = false;
        for (Activity act : ACTIVITIES) {
            if (act.getClass().getName().equals(clz.getName())) {
                isExist = true;
                break;
            }
        }
        if (!isExist) return; //不存在不进行任何操作
        //推出目标之前页面
        for (int i = ACTIVITIES.size() - 1; i >= 0; i--) {
            Activity act = ACTIVITIES.get(i);
            if (act.getClass().getName().equals(clz.getName())) { //到达目标,跳出
                break;
            }
            act.finish();
        }
    }

    /**
     * 绑定控件之前必须初始化布局
     *
     * @return 布局ID
     */
    protected abstract int getLayoutID();

    /**
     * 初始化完成，替代{@link #onCreate(Bundle)}
     */
    protected abstract void init(Bundle savedInstanceState);

    //基础控制器的View================================

    public <T> LifecycleTransformer<T> bindUntilDestroyEvent() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

    protected class BasePresenterView implements IBaseView {
        @Override
        public <T> LifecycleTransformer<T> bindUntilDestroyEvent() {
            return bindUntilEvent(ActivityEvent.DESTROY);
        }
    }

    public class BaseHttpRequestCallBack extends BasePresenterView implements IBaseHttpRequestCallBack {

        private boolean isShowProgress;

        BaseHttpRequestCallBack() {
        }

        BaseHttpRequestCallBack(boolean isShowProgress) {
            this.isShowProgress = isShowProgress;
        }

        @Override
        public void onHttpRequestStart() {
            if (isShowProgress)
                showProgress();
        }

        @Override
        public void onHttpRequestError(int code, String error) {
            LogUtils.e("ahlinyuan code:" + code + ",error:" + error);
            toast(error);
        }

        @Override
        public void onHttpRequestComplete() {
            if (isShowProgress)
                dismissProgress();
        }
    }


    //Toast====================================

    /**
     * Toast
     *
     * @param res 资源
     */
    public void toast(int res) {
        Toast.show(AppCtx, res);
    }

    /**
     * Toast
     *
     * @param text 文本
     */
    public void toast(CharSequence text) {
        Toast.show(AppCtx, text);
    }

    //进度框===========================

    private MaterialDialog mPD;

    /**
     * 显示进度框
     */
    public void showProgress() {
        if (mPD == null) {
            mPD = new MaterialDialog.Builder(this)
                    .content(R.string.loading)
                    .progress(true, 0)
                    .canceledOnTouchOutside(false)
                    .build();
//            mPD = new ProgressDialog(this);
//            mPD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            mPD.setMessage(getString(R.string.loading));	//设置内容
//            mPD.setCancelable(false);//点击屏幕和按返回键都不能取消加载框
        }
        mPD.show();
    }

    /**
     * 关闭进度框
     */
    public void dismissProgress() {
        if (mPD != null)
            mPD.dismiss();
    }
}

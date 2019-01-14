package com.ahlinyuan.infrastructure.V.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ahlinyuan.infrastructure.P.BasePresenter;
import com.ahlinyuan.infrastructure.P.factory.IPresenterFactory;
import com.ahlinyuan.infrastructure.P.factory.PresenterFactory;
import com.ahlinyuan.infrastructure.P.proxy.PresenterProxy;
import com.ahlinyuan.infrastructure.P.proxy.IPresenterProxy;
import com.ahlinyuan.infrastructure.R;
import com.ahlinyuan.infrastructure.V.IBaseView;
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
 * @author ahlinyuan
 * @date 2018/6/25
 * @description 基础页面，父Activity
 */
public abstract class BaseActivity<V extends IBaseView, P extends BasePresenter<V>> extends RxAppCompatActivity implements IPresenterProxy<V, P> {

    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private PresenterProxy<V, P> mProxy = new PresenterProxy<>(PresenterFactory.createFactory(getClass()));

    //页面列表缓存
    private static final List<Activity> ACTIVITIES = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e("V onCreate");
        LogUtils.e("V onCreate mProxy = " + mProxy);
        LogUtils.e("V onCreate this = " + this.hashCode());
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
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
    protected void onResume() {
        super.onResume();
        LogUtils.e("V onResume");
        mProxy.onResume((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("V onDestroy = " + isChangingConfigurations());
        mProxy.onDestroy();
        ACTIVITIES.remove(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.e("V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(IPresenterFactory<V, P> presenterFactory) {
        LogUtils.e("V setPresenterFactory");
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public IPresenterFactory<V, P> getPresenterFactory() {
        LogUtils.e("V getPresenterFactory");
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getPresenter() {
        LogUtils.e("V getPresenter");
        return mProxy.getPresenter();
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

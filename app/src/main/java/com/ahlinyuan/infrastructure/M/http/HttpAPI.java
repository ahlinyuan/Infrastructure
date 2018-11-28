package com.ahlinyuan.infrastructure.M.http;

import com.ahlinyuan.infrastructure.utils.LogUtil;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.ahlinyuan.infrastructure.IApplication.ISLINE;

public class HttpAPI {

    private static String URL = "http://192.168.1.123/";
    //测试URL
    public final static String URL_DEBUG = "http://192.168.1.123/";
    //正式URL
    public final static String URL_RELEASE = "http://192.168.1.123/";


    //初始化================
    /**
     * api实例
     */
    private static API API;

    /**
     * 初始化网络请求，在Application创建的时候调用,确保只初始化一次
     */
    public static void init() {
        URL = ISLINE ? URL_RELEASE : URL_DEBUG;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        if (!ISLINE) { //DEBUG模式
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            builder.client(client);
        }
        API = builder.build().create(API.class);
        LogUtil.e("网络API初始化完成");
    }

    //线程切换部分=============
    /**
     * 网络请求的线程
     */
    private static <T> Observable<T> thread(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //网络请求==================
}

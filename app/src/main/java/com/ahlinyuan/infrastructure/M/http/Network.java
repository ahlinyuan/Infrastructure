package com.ahlinyuan.infrastructure.M.http;

import com.ahlinyuan.infrastructure.M.models.BaseModel;
import com.ahlinyuan.infrastructure.utils.LogUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ahlinyuan.infrastructure.IApplication.ISLINE;

public class Network implements INetwork {

    private Network() {
    }

    private static class SingleNetwork {
        private static Network INSTANCE = new Network();
    }

    public static Network getInstance() {
        return SingleNetwork.INSTANCE;
    }

    //初始化================
    private static String URL = "http://192.168.1.xxx/";
    //测试URL
    private final static String URL_DEBUG = "http://192.168.1.xxx/";
    //正式URL
    private final static String URL_RELEASE = "http://192.168.1.xxx/";

    /**
     * api实例
     */
    private static NetworkApi NetworkApi;

    /**
     * 初始化网络请求，在Application创建的时候调用,确保只初始化一次
     */
    public static void init(String url) {
        if (url == null || url.isEmpty()) {
            URL = ISLINE ? URL_RELEASE : URL_DEBUG;
        } else {
            URL = url;
        }
        Retrofit.Builder rBuilder = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        OkHttpClient.Builder oBuilder = new OkHttpClient.Builder();
        //公共头拦截器
        oBuilder.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
//                    .header("token", "")
                    .header("version", "1.0")
//                    .header("language", "")
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });
        //DEBUG模式,添加日志拦截器
        if (!ISLINE) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            oBuilder.addInterceptor(interceptor);
        }
        rBuilder.client(oBuilder.build());

        NetworkApi = rBuilder.build().create(NetworkApi.class);
        LogUtils.e("网络API初始化完成");
    }

    //线程切换部分=============

    /**
     * 网络请求的线程
     */
    private static <T> Observable<T> thread(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    //网络请求==================


    @Override
    public Observable<BaseModel> checkUpdate(int versions) {
        RequestParam rp = new RequestParam();
        rp.put("versions", versions);
        LogUtils.e("ahlinyuan RequestParam:" + rp.toString());
        return thread(NetworkApi.checkUpdate(rp.toString()));
    }
}

package com.ahlinyuan.infrastructure.M.http;

import com.ahlinyuan.infrastructure.M.models.BaseModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkApi {

    /**
     * 参数名
     */
    String API_PARAM = "param";

    @POST("app/update/")
    @FormUrlEncoded
    Observable<BaseModel> checkUpdate(@Field(API_PARAM)String jsonString);
}

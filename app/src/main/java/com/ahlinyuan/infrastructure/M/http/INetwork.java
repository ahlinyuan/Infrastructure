package com.ahlinyuan.infrastructure.M.http;

import com.ahlinyuan.infrastructure.M.models.BaseModel;

import io.reactivex.Observable;

public interface INetwork {

    /**
     * 检查更新（示例）
     * @return
     */
    Observable<BaseModel> checkUpdate(int versions);
}

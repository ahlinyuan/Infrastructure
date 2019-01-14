package com.ahlinyuan.infrastructure.M.http;

import com.ahlinyuan.infrastructure.M.models.BaseModel;

import io.reactivex.Observable;

public interface INetwork {

    /**
     * 更新检查（示例）
     * @param versions 当前版本
     * @return 接口返回数据
     */
    Observable<BaseModel> checkUpdate(int versions);
}

package com.ahlinyuan.infrastructure.M.models;

import java.util.List;

/**
 * 基础数据列表Model
 */
public class BaseEntitiesModel<T> extends BaseModel {

    //对象列表
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

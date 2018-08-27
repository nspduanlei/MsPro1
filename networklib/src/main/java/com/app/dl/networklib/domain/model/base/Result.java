package com.app.dl.networklib.domain.model.base;

public class Result<T> extends ComResult {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

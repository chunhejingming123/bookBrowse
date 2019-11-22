package com.wenli.framework.mvp;

public interface OnCallBackListener<T> {
    void onSucess(T t);
    void onFailed(String e);
}

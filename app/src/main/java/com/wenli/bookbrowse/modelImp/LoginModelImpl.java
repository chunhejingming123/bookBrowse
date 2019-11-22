package com.wenli.bookbrowse.modelImp;


import com.greenengerapp.api.ApiManager;
import com.wenli.framework.http.HttpRespnse;
import com.wenli.framework.mvp.OnCallBackListener;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class LoginModelImpl {

    public void loginWx(final OnCallBackListener mCallBack) {

        mCallBack.onSucess("sucess");
    }

    public void login(String url, Map<String, String> map, final OnCallBackListener mCallback) {
        ApiManager.Companion.getGetInstance().loadDataByParmars(url, map, new HttpRespnse() {
            @Override
            public void _onSucess(@NotNull String t) {
                mCallback.onSucess(t);
            }


            @Override
            public void _onError(@NotNull Throwable e) {
                mCallback.onFailed(e.getLocalizedMessage());

            }
        });

    }

}

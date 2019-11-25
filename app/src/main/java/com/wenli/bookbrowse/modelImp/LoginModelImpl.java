package com.wenli.bookbrowse.modelImp;


import android.text.TextUtils;

import com.greenengerapp.api.ApiInterface;
import com.greenengerapp.api.ApiManager;
import com.wenli.bookbrowse.bean.User;
import com.wenli.framework.http.HttpRespnse;
import com.wenli.framework.http.UriConsts;
import com.wenli.framework.mvp.OnCallBackListener;
import com.wenli.framework.util.GsonUtil;
import com.wenli.framework.util.LogUtil;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
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

    public static void loginByCodeWx(String code, final OnCallBackListener mCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("appid", ApiInterface.Companion.getWxAppid());
        map.put("secret", ApiInterface.Companion.getWxSecret());
        map.put("code", code);
        map.put("grant_type", "authorization_code");

        ApiManager.Companion.getGetInstance().getDataByParmars("sns/oauth2/access_token?", map, new HttpRespnse() {
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

    public static void getUserConfig(String accessToken, String openid, final OnCallBackListener<User> mCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("openid", openid);
        map.put("access_token", accessToken);
        ApiManager.Companion.getGetInstance().getDataByParmars("sns/userinfo?", map, new HttpRespnse() {
            @Override
            public void _onSucess(@NotNull String t) {
                if (TextUtils.isEmpty(t)) {
                    return;
                }
                LogUtil.e("Tag","-------t="+t);
                User mUser = GsonUtil.Companion.GsonToBean(t, User.class);
                mCallback.onSucess(mUser);
            }


            @Override
            public void _onError(@NotNull Throwable e) {
                mCallback.onFailed(e.getLocalizedMessage());

            }
        });
    }

}

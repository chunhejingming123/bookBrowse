package com.wenli.bookbrowse.presenter;

import com.wenli.bookbrowse.contract.LoginContract;
import com.wenli.bookbrowse.modelImp.LoginModelImpl;
import com.wenli.framework.mvp.OnCallBackListener;
import com.wenli.framework.util.ToastUtil;

import java.util.Map;

public class LoginPresenter extends LoginContract.AbstractloginPresenter {

    private LoginModelImpl mModel = new LoginModelImpl();

    public LoginPresenter() {
    }

    @Override
    public void loginPhone(String url, Map<String, String> map) {
        if (getView() == null) {
            return;
        }

    }

    @Override
    public void loginWx() {
        if (getView() == null) {
            return;
        }
        mModel.loginWx(new OnCallBackListener() {
            @Override
            public void onSucess(Object o) {
                getView().showToastLogin("loginsucess");
            }

            @Override
            public void onFailed(String e) {

            }
        });

    }

    @Override
    public void loginVip() {

    }


}

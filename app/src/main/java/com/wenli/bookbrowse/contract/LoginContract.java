package com.wenli.bookbrowse.contract;

import com.wenli.framework.mvp.BasePresenter;
import com.wenli.framework.mvp.BaseView;

import java.util.Map;

public interface LoginContract {
    interface ILoginView extends BaseView {
        void showToastLogin(String msg);

    }

    abstract class AbstractloginPresenter extends BasePresenter<ILoginView> {
        public abstract void loginPhone(String url, Map<String, String> map);

        public abstract void loginWx();

        public abstract void loginVip();

    }
}

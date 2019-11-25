package com.wenli.bookbrowse.base;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.wenli.bookbrowse.bean.User;
import com.wenli.framework.base.BaseApplication;
import com.wenli.framework.util.MyUncaughtExceptionHandler;
import com.wenli.framework.util.ScreenUtil;
import com.wenli.framework.util.SharedPreferencesHelper;

public class MyApplication extends BaseApplication {

    private static User mUser;

    @Override
    public void onCreate() {
        super.onCreate();
        MyUncaughtExceptionHandler.with(this);
        ScreenUtil.initScreen(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public static void setUser(User user) {
        mUser = user;
        SharedPreferencesHelper.getInstance(BaseApplication.Companion.getApplication()).putObject(user);
    }

    public static User getUser() {
        if (null == mUser) {
            mUser = SharedPreferencesHelper.getInstance(BaseApplication.Companion.getApplication()).
                    getObject(User.class);
        }
        return mUser;
    }
}

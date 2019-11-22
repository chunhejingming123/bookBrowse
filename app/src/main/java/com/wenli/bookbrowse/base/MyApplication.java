package com.wenli.bookbrowse.base;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.wenli.framework.base.BaseApplication;
import com.wenli.framework.util.MyUncaughtExceptionHandler;
import com.wenli.framework.util.ScreenUtil;

public class MyApplication extends BaseApplication {

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
}

package com.wenli.bookbrowse.login;

import android.view.Window;
import android.view.WindowManager;

import com.wenli.bookbrowse.HomeActivity;
import com.wenli.bookbrowse.R;
import com.wenli.framework.base.BaseActivity;
import com.wenli.framework.util.ParmarsValue;
import com.wenli.framework.util.SharedPreferencesHelper;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class SplashActivity extends BaseActivity {
    @Override
    public void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public int onLayoutLoad() {
        return R.layout.activity_splash;
    }

    @Override
    public void findView() {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        rendView();
                    }
                });

    }

    @Override
    public void rendView() {
        startActivity(HomeActivity.class, null);
        finish();

    }
}

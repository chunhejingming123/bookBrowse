package com.wenli.framework.mvp;

import android.os.Bundle;

import com.wenli.framework.base.BaseActivity;
import com.wenli.framework.util.GreenActivityManager;

import org.jetbrains.annotations.Nullable;

public abstract class MvpBaseActivity<V, T extends BasePresenter<V>> extends BaseActivity {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        if (0 != onLayoutLoad()) {
            setContentView(onLayoutLoad());
        }
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
        findView();
        GreenActivityManager.INSTANCE.registerActivity(this);
        onloadData();
    }

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}

package com.wenli.framework.mvp;

import android.os.Bundle;

import com.wenli.framework.base.BaseFragment;

import org.jetbrains.annotations.Nullable;

public abstract class MvpBaseFragment <V,T extends BasePresenter<V>>extends BaseFragment {
    protected T mPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=createPresenter();
        if (null!=mPresenter){
            mPresenter.attachView((V) this);
        }

    }

    protected abstract T createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null!=mPresenter){
            mPresenter.detachView();
        }
    }
}

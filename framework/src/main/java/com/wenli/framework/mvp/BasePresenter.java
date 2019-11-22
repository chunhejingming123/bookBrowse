package com.wenli.framework.mvp;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class BasePresenter<V> {
    /**
     * View 接口类型的弱引用
     */
    private Reference<V> mViewRef;
    private V mProxyView;

    /**
     * 绑定视图
     */
    public void attachView(V mView) {
        mViewRef = new WeakReference<>(mView);
        mProxyView = (V) Proxy.newProxyInstance(
                mView.getClass().getClassLoader(),
                mView.getClass().getInterfaces(),
                new MvpViewHandler(null));
    }

    protected V getView() {
        if (isViewAttached()) {
            return mViewRef.get();
        } else {
            return null;
        }
    }

    private boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 解除视图
     */
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * View代理类  防止 页面关闭P异步操作调用V 方法 空指针问题
     */
    private class MvpViewHandler implements InvocationHandler {

        private BaseView mvpView;

        MvpViewHandler(BaseView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //如果V层没被销毁, 执行V层的方法.
            if (isViewAttached()) {
                return method.invoke(mvpView, args);
            } //P层不需要关注V层的返回值
            return null;
        }
    }
}

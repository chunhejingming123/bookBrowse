package com.wenli.bookbrowse.dialoge;

import android.app.Activity;

public class DialogeUtil {
    public static LoginDialoge loginDialge(Activity mActivity, final DialogeCallBack mCallBack) {
        LoginDialoge dialoge = new LoginDialoge(mActivity);
        dialoge.setDialogeDirect(new LoginDialoge.DialogeDirect() {
            @Override
            public void action() {
                if (null != mCallBack) {
                    mCallBack.callBack();
                }
            }
        });
        return dialoge;

    }

    public static VipOpenDialoge vipOpenDiaoge(Activity mActivity, final DialogeCallBack mCallBack) {
        VipOpenDialoge dialoge = new VipOpenDialoge(mActivity);
        dialoge.setDialogeDirect(new VipOpenDialoge.DialogeDirect() {
            @Override
            public void action() {
                if (null != mCallBack) {
                    mCallBack.callBack();
                }
            }
        });
        return dialoge;

    }

    public static VipConfirmDialoge vipOpenConfirmDiaoge(Activity mActivity, final DialogeCallBack mCallBack) {
        VipConfirmDialoge dialoge = new VipConfirmDialoge(mActivity);
        dialoge.setDialogeDirect(new VipConfirmDialoge.DialogeDirect() {
            @Override
            public void action() {
                if (null != mCallBack) {
                    mCallBack.callBack();
                }
            }
        });
        return dialoge;

    }

    /**
     * vip 充值成功
     *
     * @param mActivity
     * @param mCallBack
     * @return
     */
    public static VipSucessDialoge vipSucessDiaoge(Activity mActivity, final DialogeCallBack mCallBack) {
        VipSucessDialoge dialoge = new VipSucessDialoge(mActivity);
        dialoge.setDialogeDirect(new VipSucessDialoge.DialogeDirect() {
            @Override
            public void action() {
                if (null != mCallBack) {
                    mCallBack.callBack();
                }
            }
        });
        return dialoge;

    }

    /**
     * 借书失败
     *
     * @param mActivity
     * @param mCallBack
     * @return
     */
    public static BookFailerDialoge boookFailDialoge(Activity mActivity, final DialogeCallBack mCallBack) {
        BookFailerDialoge dialoge = new BookFailerDialoge(mActivity);
        dialoge.setDialogeDirect(new BookFailerDialoge.DialogeDirect() {
            @Override
            public void action() {
                if (null != mCallBack) {
                    mCallBack.callBack();
                }
            }
        });
        return dialoge;

    }

    /**
     * 借书成功
     *
     * @param mActivity
     * @param mCallBack
     * @return
     */
    public static BookSucessDialoge boookSucessDiaoge(Activity mActivity, final DialogeCallBack mCallBack) {
        BookSucessDialoge dialoge = new BookSucessDialoge(mActivity);
        dialoge.setDialogeDirect(new BookSucessDialoge.DialogeDirect() {
            @Override
            public void action() {
                if (null != mCallBack) {
                    mCallBack.callBack();
                }
            }
        });
        return dialoge;

    }


    public static ZxingDialoge zxingDialge(Activity mActivity, final DialogeCallBack mCallBack) {
        ZxingDialoge dialoge = new ZxingDialoge(mActivity);
        dialoge.setZxingResume(new ZxingDialoge.ZxingResume() {
            @Override
            public void close() {

            }
        });
        return dialoge;

    }


    private DialogeCallBack mCallBack;


    public interface DialogeCallBack {
        void close();

        void callBack();
    }
}

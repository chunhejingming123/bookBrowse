package com.wenli.framework.util;

import android.util.Log;

import com.wenli.framework.http.UriConsts;


/**
 * 作者 :created  by suochunming
 * 日期：2018/8/20 0020:18
 */

public class LogUtil {
    public static void d(String tag, String msg) {
        if (UriConsts.Companion.isDebug())
            Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable t) {
        if (UriConsts.Companion.isDebug())
            Log.d(tag, msg, t);
    }

    public static void i(String tag, String msg) {
        if (UriConsts.Companion.isDebug())
            Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable t) {
        if (UriConsts.Companion.isDebug())
            Log.i(tag, msg, t);
    }

    public static void w(String tag, String msg) {
        if (UriConsts.Companion.isDebug())
            Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable t) {
        if (UriConsts.Companion.isDebug())
            Log.w(tag, msg, t);
    }

    public static void e(String tag, String msg) {
        if (UriConsts.Companion.isDebug())
            Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable t) {
        if (UriConsts.Companion.isDebug())
            Log.e(tag, msg, t);
    }

    /**
     * 打印error级别信息
     *
     * @param msg
     */
    public static void e(Object msg) {
        if (UriConsts.Companion.isDebug()) {
            Log.e("lnke:", msg == null ? "" : msg.toString());
        }
    }


}

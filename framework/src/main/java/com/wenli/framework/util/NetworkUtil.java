package com.wenli.framework.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断有无网络
 */
public class NetworkUtil {
    public static boolean isOpenNetwork(Context context, boolean showToast) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            int networkType = networkInfo.getType();
            if (ConnectivityManager.TYPE_WIFI == networkType) {
                //当前为wifi网络
            } else if (ConnectivityManager.TYPE_MOBILE == networkType) {
                //当前为mobile网络
            }
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        if (showToast) {
            ToastUtil.showAlter("网络已断开");

        }
        return false;
    }
}

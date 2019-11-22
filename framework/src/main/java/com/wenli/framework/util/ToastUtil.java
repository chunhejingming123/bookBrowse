package com.wenli.framework.util;

import android.text.TextUtils;
import android.widget.Toast;

import com.wenli.framework.base.BaseApplication;


public class ToastUtil {


    private static void showBaseStr(String var) {
        if (TextUtils.isEmpty(var)) {
            return;
        }
        Toast.makeText(BaseApplication.Companion.getApplication(), var, Toast.LENGTH_SHORT).show();
    }

    public static void showAlter(String var) {
        showBaseStr(var);
    }

}

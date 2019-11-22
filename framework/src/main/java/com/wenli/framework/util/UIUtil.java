package com.wenli.framework.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 *
 */
public class UIUtil {
    private static final String LOGTAG = UIUtil.class.getSimpleName();

    public static ProgressDialog progressDialog = null;
    private static int _screenWidth = 0;
    private static int _screenHeigh = 0;

    public static boolean stringIsEmpty(String string) {
        if (string != null) {
            string = string.replace(" ", "");
            if (string.length() > 0) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }

    /**
     * 显示水平进度条
     *
     * @param context
     * @param title
     * @param max
     */
    public static void showHorizontal(Context context, String title, int max) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(title);
        progressDialog.setMessage("请稍候...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setProgressNumberFormat("%1d kb/%2d kb");
        if (max > 0)
            progressDialog.setMax(max);
        progressDialog.show();
    }

    /**
     * 设置水平进度条最大值
     *
     * @param max
     */
    public static void setHorizontalMax(int max) {
        if (progressDialog != null) {
            if (progressDialog.getMax() != max)
                progressDialog.setMax(max);
        }
    }

    /**
     * 设置水平进度条进度
     *
     * @param i
     */
    public static void setHorizontalProgress(int i) {
        if (progressDialog != null) {
            progressDialog.setProgress(i);
        }
    }

    /**
     * 调用Toast显示，显示给定的字符串
     *
     * @param context
     * @param str
     */
    public static void showToast(Context context, String str) {
        Toast t = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        t.show();
    }

    /**
     * 调用Toast显示，显示给定的字符串资源id
     *
     * @param context
     * @param resId
     */
    public static void showToast(Context context, int resId) {
        Toast t = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        t.show();
    }

    /**
     * 显示确认对话框
     *
     * @param context
     * @param sTitle
     * @param sMessage
     */
    public static void showDialog(Context context, String sTitle, String sMessage) {
        new AlertDialog.Builder(context)
                .setTitle(sTitle)
                .setMessage(sMessage)
                .setPositiveButton("确定", null)
                .show();
    }

    /**
     * 显示进度条
     *
     * @param context
     * @param message
     */
    public static void showProgressDialog(Context context, CharSequence message) {
        if (message != null) {
            String cs = message.toString();
            if (StringToolkit.isEmpty(cs)) {
                cs = "加载中，请稍等...";
            }
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            progressDialog = new ProgressDialog(context);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(cs);
            progressDialog.show();
        }
    }

    /**
     * 注销进度条
     */
    public static void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    //获取屏幕的宽度
    public static int getScreenWidth(Context context) {
        if (_screenWidth == 0) {
            WindowManager manager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            _screenWidth = point.x;
        }
        return _screenWidth;
    }

    //获取屏幕的高度
    public static int getScreenHeight(Context context) {
        if (_screenHeigh == 0) {
            WindowManager manager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            _screenHeigh = point.y;
        }
        return _screenHeigh;
    }

    /**
     * 控制按钮不可用时间
     *
     * @param view
     */
    public static void btnWaitControl(final View view) {
        int sendConnectOutTime = 3 * 1000;//设定按钮不可用时间间隔为5秒
        view.setEnabled(false);
        Handler sendHandler = new Handler();
        sendHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    if (view != null) {
                        view.setEnabled(true);
                    }
                } catch (Exception e) {
                    Log.e(LOGTAG, "sendHandler error:" + e.toString());
                }
            }
        }, sendConnectOutTime);//按钮不可用时间控制
    }


}

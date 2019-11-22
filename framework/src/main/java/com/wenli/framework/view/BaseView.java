package com.wenli.framework.view;

import android.content.Context;

import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;

import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 作者 :created  by suochunming
 * 日期：2018/8/22 0022:09
 */

public class BaseView extends FrameLayout {

    public BaseView(@NonNull Context context) {
        super(context);
        setParmars();
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setParmars();
    }

    public BaseView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setParmars();
    }

    public void setParmars() {
        LayoutParams lytp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lytp.gravity = Gravity.CENTER;
        setLayoutParams(lytp);
    }
}

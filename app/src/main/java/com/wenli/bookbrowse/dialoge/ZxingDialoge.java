package com.wenli.bookbrowse.dialoge;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wenli.bookbrowse.R;
import com.wenli.framework.util.ScreenUtil;

import java.lang.ref.SoftReference;

public class ZxingDialoge implements View.OnClickListener {
    private Dialog mDialoge;
    private SoftReference<Activity> soft = null;

    private ImageView ivClose;
    private TextView tvResult;

    public ZxingDialoge(Activity mActivity) {
        soft = new SoftReference<Activity>(mActivity);
        if (null == soft.get()) {
            return;
        }
        if (null == mDialoge) {
            mDialoge = new Dialog(soft.get(), R.style.dialog);
            initView();
        }

    }

    // 初始化
    private void initView() {
        View view = LayoutInflater.from(soft.get()).inflate(R.layout.dialoge_zxing, null);
        mDialoge.setContentView(view);

        ivClose = view.findViewById(R.id.ivClose);
        tvResult = view.findViewById(R.id.tvResult);
        ivClose.setOnClickListener(this);

        Window window = mDialoge.getWindow();// 获取Window对象
        WindowManager.LayoutParams lp = window.getAttributes();
        ScreenUtil.initScreen(soft.get());
        lp.width = (int) (1.0f * ScreenUtil.getScreenW());
        window.setGravity(Gravity.TOP);
        mDialoge.getWindow().setAttributes(lp);
        mDialoge.setCanceledOnTouchOutside(false);
        mDialoge.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK && keyEvent.getRepeatCount() == 0) {
                    hide();
                    soft.get().finish();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivClose:
                if (null != zxingResume) {
                    zxingResume.close();
                }
                hide();
                break;
        }
    }

    public void hide() {
        if (mDialoge != null) {
            mDialoge.dismiss();
        }
    }

    public void showDialoge(String result) {
        if (mDialoge != null) {
            mDialoge.show();
            tvResult.setText(result);
        }
    }

    private ZxingResume zxingResume;

    public void setZxingResume(ZxingResume zxingResume) {
        this.zxingResume = zxingResume;
    }

    public interface ZxingResume {
        void close();
    }

}

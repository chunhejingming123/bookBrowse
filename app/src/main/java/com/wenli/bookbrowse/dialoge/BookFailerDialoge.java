package com.wenli.bookbrowse.dialoge;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.wenli.bookbrowse.R;
import com.wenli.framework.util.ScreenUtil;

import java.lang.ref.SoftReference;

public class BookFailerDialoge implements View.OnClickListener {
    private Dialog mDialoge;
    private SoftReference<Activity> soft = null;
    private Button btnlogin;
    private ImageView ivClose;

    public BookFailerDialoge(Activity mActivity) {
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
        View view = LayoutInflater.from(soft.get()).inflate(R.layout.dialoge_book_failer, null);
        mDialoge.setContentView(view);

        ivClose = view.findViewById(R.id.ivClose);
        btnlogin = view.findViewById(R.id.btnKonw);
        ivClose.setOnClickListener(this);
        btnlogin.setOnClickListener(this);
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
                hide();
                break;
            case R.id.btnKonw:
                mDialogeDirect.action();
                hide();
                break;
        }
    }

    public void hide() {
        if (mDialoge != null) {
            mDialoge.dismiss();
        }
    }

    public void showDialoge() {
        if (mDialoge != null) {
            mDialoge.show();
        }
    }

    private DialogeDirect mDialogeDirect;

    public void setDialogeDirect(DialogeDirect mDialogeDirect) {
        this.mDialogeDirect = mDialogeDirect;
    }

    public interface DialogeDirect {
        void action();
    }
}

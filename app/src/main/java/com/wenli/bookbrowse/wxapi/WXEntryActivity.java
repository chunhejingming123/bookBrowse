package com.wenli.bookbrowse.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wenli.bookbrowse.HomeActivity;
import com.wenli.bookbrowse.R;
import com.wenli.bookbrowse.modelImp.LoginModelImpl;
import com.wenli.framework.base.BaseActivity;
import com.wenli.framework.mvp.OnCallBackListener;
import com.wenli.framework.util.LogUtil;
import com.wenli.framework.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (2 == baseResp.getType()) {
            finish();
            return;
        }

        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                //用户拒绝授权
                ToastUtil.showAlter("拒绝授权微信登录");
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                //用户取消
                String message = "取消了微信登录";
                ToastUtil.showAlter(message);
                break;
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;

                //这里拿到了这个code，去做2次网络请求获取access_token和用户个人信息
                LoginModelImpl.loginByCodeWx(code, new OnCallBackListener<String>() {
                    @Override
                    public void onSucess(String o) {
                        LogUtil.e("Tag", "-----string=" + o);
                        try {
                            JSONObject obj = new JSONObject(o);
                            String access_token = obj.getString("access_token");
                            String openId = obj.getString("openid");
                            Bundle mBundl = new Bundle();
                            mBundl.putString("assToken", access_token);
                            mBundl.putString("openId", openId);
                            startActivity(HomeActivity.class, mBundl);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailed(String e) {

                    }
                });
                break;
        }
        finish();

    }

    @Override
    public int onLayoutLoad() {
        return R.layout.activity_wxentry;
    }

    @Override
    public void findView() {
        api = WXAPIFactory.createWXAPI(this, null);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }
}

package com.wenli.bookbrowse.wxapi;

import android.content.Intent;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wenli.bookbrowse.R;
import com.wenli.framework.base.BaseActivity;
import com.wenli.framework.util.ToastUtil;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    public void onReq(BaseReq baseReq) {
        ToastUtil.showAlter("ooo");
    }

    @Override
    public void onResp(BaseResp baseResp) {
        int type = baseResp.getType(); //类型：分享还是登录
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
                ToastUtil.showAlter(code);
                //这里拿到了这个code，去做2次网络请求获取access_token和用户个人信息
                // WXLoginUtils().getWXLoginResult(code, this);
                break;
        }

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

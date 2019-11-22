package com.wenli.bookbrowse.login;

import android.view.View;
import android.widget.Button;

import com.greenengerapp.api.ApiInterface;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wenli.bookbrowse.HomeActivity;
import com.wenli.bookbrowse.R;
import com.wenli.bookbrowse.contract.LoginContract;
import com.wenli.bookbrowse.presenter.LoginPresenter;
import com.wenli.framework.http.Api;
import com.wenli.framework.mvp.MvpBaseActivity;
import com.wenli.framework.util.ParmarsValue;
import com.wenli.framework.util.SharedPreferencesHelper;
import com.wenli.framework.util.ToastUtil;

public class LoginActivity extends MvpBaseActivity<LoginContract.ILoginView, LoginPresenter> implements LoginContract.ILoginView, View.OnClickListener {
    private Button btnLogin;
    private View wxLogin;

    @Override
    public int onLayoutLoad() {
        return R.layout.activity_login;
    }

    @Override
    public void findView() {
        registerToWX();
        btnLogin = findViewById(R.id.btnLogin);
        wxLogin = findViewById(R.id.llwx);
        btnLogin.setOnClickListener(this);
        wxLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                startActivity(HomeActivity.class, null);
                SharedPreferencesHelper.getInstance(this).put(ParmarsValue.KEY_lOGIN, true);
                break;
            case R.id.llwx:

                mPresenter.loginWx();
                break;
        }
    }

    IWXAPI mWxApi;
    private void registerToWX() {
        //第二个参数是指你应用在微信开放平台上的AppID
        mWxApi = WXAPIFactory.createWXAPI(this, ApiInterface.Companion.getWxAppid(), false);
        // 将该app注册到微信
        mWxApi.registerApp(ApiInterface.Companion.getWxAppid());
    }

    @Override
    public void showToastLogin(String msg) {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        //像微信发送请求
        mWxApi.sendReq(req);

    }


    @Override
    public void showError(String error) {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }
}

package com.chf.wanandroid.mvp.presenter;

import android.content.Context;

import com.chf.wanandroid.base.BasePresenter;
import com.chf.wanandroid.mvp.view.LoginView;

/**
 *
 * @author 17919
 * @date 2018/4/8
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(Context context, LoginView iView) {
        super(context, iView);
    }

    public void goRegister() {

    }
}

package com.chf.wanandroid.mvp.presenter;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;

import com.chf.wanandroid.base.BasePresenter;
import com.chf.wanandroid.http.BaseCompose;
import com.chf.wanandroid.http.ErrorConsumer;
import com.chf.wanandroid.http.OiApiManager;
import com.chf.wanandroid.mvp.model.bean.UserBean;
import com.chf.wanandroid.mvp.view.LoginView;
import com.chf.wanandroid.ui.base.BaseConfig;
import com.chf.wanandroid.ui.events.RefreshEvent;
import com.chf.wanandroid.ui.activity.RegisterActivity;
import com.chf.wanandroid.ui.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.functions.Consumer;

/**
 * @author 17919
 * @date 2018/4/8
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(Context context, LoginView iView) {
        super(context, iView);
    }

    public void goRegister() {
        RegisterActivity.actionStart(mContext);
    }

    public void goLogin(String pwd, String userName) {
        SharedPreferencesUtil.put(mContext, BaseConfig.USER_NAME, userName);
        SharedPreferencesUtil.put(mContext, BaseConfig.USER_PWD, pwd);
        iView.showProgress();
        disposable = OiApiManager.getApiServie().goLogin(userName, pwd)
                .compose(new BaseCompose<UserBean>())
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(UserBean userBean) throws Exception {
                        EventBus.getDefault().post(new RefreshEvent(1));
                        iView.hideProgress();
                        iView.loginSuccess(userBean);
                    }
                }, new ErrorConsumer(mContext) {
                    @Override
                    public void onError(Throwable throwable) throws Exception {
                        super.onError(throwable);
                        iView.hideProgress();
                    }
                });
    }


    /**
     * 检查参数
     *
     * @param pwd
     * @param username
     * @param loginPasswordTil
     * @param loginUsernameTil
     * @return
     */
    public boolean checkParams(String pwd, String username, TextInputLayout loginPasswordTil, TextInputLayout loginUsernameTil) {

        if (TextUtils.isEmpty(username)) {
            loginUsernameTil.setError("用户名不能为空");
            return false;
        }

        if (TextUtils.isEmpty(pwd)) {
            loginPasswordTil.setError("密码不能为空");
            return false;
        }
        return true;
    }
}

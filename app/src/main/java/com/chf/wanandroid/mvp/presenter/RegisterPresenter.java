package com.chf.wanandroid.mvp.presenter;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;

import com.chf.wanandroid.base.BasePresenter;
import com.chf.wanandroid.http.BaseCompose;
import com.chf.wanandroid.http.ErrorConsumer;
import com.chf.wanandroid.http.OiApiManager;
import com.chf.wanandroid.mvp.model.bean.UserBean;
import com.chf.wanandroid.mvp.view.RegisterView;

import io.reactivex.functions.Consumer;

/**
 * @author 17919
 * @date 2018/4/9
 */

public class RegisterPresenter extends BasePresenter<RegisterView> {

    public RegisterPresenter(Context context, RegisterView iView) {
        super(context, iView);
    }

    /**
     * 注册
     *
     * @param userName
     * @param pwd
     * @param rePwd
     */
    public void goRegister(String userName, String pwd, String rePwd) {
        disposable = OiApiManager.getApiServie().register(userName, pwd, rePwd)
                .compose(new BaseCompose<UserBean>())
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(UserBean userBean) throws Exception {

                    }
                }, new ErrorConsumer(mContext));
    }

    /**
     * 检查参数
     *
     * @param userName
     * @param pwd
     * @param rePwd
     * @param userNameLayout
     * @param pwdLayout
     * @param rePwdLayout
     */
    public boolean checkParams(String userName, String pwd, String rePwd,
                               TextInputLayout userNameLayout, TextInputLayout pwdLayout, TextInputLayout rePwdLayout) {
        if (TextUtils.isEmpty(userName)) {
            userNameLayout.setError("用户名不能为空");
            return false;
        }
        if (userName.length() < 3 || userName.length() > 16) {
            userNameLayout.setError("用户名长度需要在3~16位");
            return false;
        }

        if (TextUtils.isEmpty(pwd)) {
            pwdLayout.setError("密码不能为空");
            return false;
        }

        if (pwd.length() < 3 || pwd.length() > 16) {
            userNameLayout.setError("密码长度需要在3~16位");
            return false;
        }

        if (TextUtils.isEmpty(rePwd)) {
            rePwdLayout.setError("密码不能为空");
            return false;
        }

        if (rePwd.length() < 3 || rePwd.length() > 16) {
            userNameLayout.setError("密码长度需要在3~16位");
            return false;
        }


        if (TextUtils.equals(pwd, rePwd)) {
            rePwdLayout.setError("两次密码不一样");
            return false;
        }


        return true;
    }
}

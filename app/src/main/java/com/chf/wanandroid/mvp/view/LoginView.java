package com.chf.wanandroid.mvp.view;

import com.chf.wanandroid.base.BaseView;
import com.chf.wanandroid.mvp.model.bean.UserBean;

/**
 *
 * @author 17919
 * @date 2018/4/8
 */

public interface LoginView extends BaseView {
    /**
     * 登录成功
     * @param userBean
     */
    void loginSuccess(UserBean userBean);

    void showProgress();

    void hideProgress();
}

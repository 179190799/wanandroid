package com.chf.wanandroid.mvp.view;

import android.text.TextWatcher;

import com.chf.wanandroid.base.BaseView;
import com.chf.wanandroid.mvp.model.bean.UserBean;

/**
 * @author 17919
 * @date 2018/4/9
 */

public interface RegisterView extends BaseView, TextWatcher {
    /**
     * 注册成功
     *
     * @param userBean
     */
    void registerSuccess(UserBean userBean);

    void showProgress();

    void hideProgress();
}

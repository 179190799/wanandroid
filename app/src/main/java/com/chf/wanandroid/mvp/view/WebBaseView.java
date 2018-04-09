package com.chf.wanandroid.mvp.view;


import com.chf.wanandroid.base.BaseView;

/**
 * normal WebBaseView 基类
 *
 * @author Administrator
 * @date 2018/1/11
 */

public interface WebBaseView extends BaseView {
    void setWebTitle(String title);

    void openFailed();
}

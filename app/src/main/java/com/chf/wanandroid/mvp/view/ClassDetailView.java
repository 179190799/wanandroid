package com.chf.wanandroid.mvp.view;

import com.chf.wanandroid.base.BaseView;
import com.chf.wanandroid.mvp.model.bean.ArticleBean;

/**
 * Created by Administrator on 2018/4/11.
 */

public interface ClassDetailView extends BaseView {
    void showView(ArticleBean articleBean);

    void showErrorView(String msg);
}

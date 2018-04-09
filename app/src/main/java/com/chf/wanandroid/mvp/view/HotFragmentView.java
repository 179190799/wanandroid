package com.chf.wanandroid.mvp.view;

import com.chf.wanandroid.base.BaseView;
import com.chf.wanandroid.mvp.model.bean.HotKeyWordBean;
import com.chf.wanandroid.mvp.model.bean.HotWebBean;

import java.util.List;

/**
 * 热门
 *
 * @author 17919
 * @date 2018/3/20
 */

public interface HotFragmentView extends BaseView {
    void showHotKeyView(List<HotKeyWordBean> keyWordBeans);

    void showHotWebView(List<HotWebBean> webBeans);

    void showProgress();

    void hideProgress();

    void showErrorView();

    void initRecyclerView();
}

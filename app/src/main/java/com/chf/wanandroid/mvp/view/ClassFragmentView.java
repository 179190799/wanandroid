package com.chf.wanandroid.mvp.view;

import com.chf.wanandroid.base.BaseView;
import com.chf.wanandroid.mvp.model.bean.ClassBean;

import java.util.List;

/**
 * 体系
 *
 * @author 17919
 * @date 2018/3/20
 */

public interface ClassFragmentView extends BaseView {
    void showView(List<ClassBean> classBeans);

    void initRecyclerView();

    void showErrorView();
}

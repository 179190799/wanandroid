package com.chf.wanandroid.mvp.view;

import com.chf.wanandroid.base.BaseView;
import com.chf.wanandroid.mvp.model.HomeModel;

/**
 * 首页
 *
 * @author 17919
 * @date 2018/3/20
 */

public interface HomeFragmentView extends BaseView {

    void showView(HomeModel model);

    /**
     * 没有更多数据
     */
    void noLoadMore();

    /**
     * 加载更多数据
     * @param model
     */
    void loadMoreData(HomeModel model);

    void initRecyclerView();
}

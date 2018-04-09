package com.chf.wanandroid.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.chf.wanandroid.R;
import com.chf.wanandroid.base.BaseToolBarActivity;
import com.chf.wanandroid.mvp.presenter.MyCollectionPresenter;
import com.chf.wanandroid.mvp.view.MyCollectionView;
import com.chf.wanandroid.ui.widget.AutoRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的收藏
 *
 * @author 17919
 * @date 2018/4/8
 */

@SuppressLint("Registered")
public class MyCollectionActivity extends BaseToolBarActivity<MyCollectionPresenter> implements MyCollectionView, OnLoadmoreListener, OnRefreshListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;

    @BindView(R.id.recycler_view)
    AutoRecyclerView recyclerView;
    @BindView(R.id.refresh_view)
    SmartRefreshLayout refreshView;
    @BindView(R.id.common_refresh_layout)
    LinearLayout commonRefreshLayout;

    @Override
    public void initView() {
        refreshView.setOnLoadmoreListener(this);
        refreshView.setOnRefreshListener(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initPresenter() {
        presenter = new MyCollectionPresenter(this, this);
        presenter.init();
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MyCollectionActivity.class);
        context.startActivity(intent);
    }
}

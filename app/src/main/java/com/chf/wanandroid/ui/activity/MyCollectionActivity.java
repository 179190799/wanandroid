package com.chf.wanandroid.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chf.wanandroid.R;
import com.chf.wanandroid.base.BaseToolBarActivity;
import com.chf.wanandroid.mvp.model.bean.MyCollectionBean;
import com.chf.wanandroid.mvp.presenter.MyCollectionPresenter;
import com.chf.wanandroid.mvp.view.MyCollectionView;
import com.chf.wanandroid.ui.adapter.MyCollectionAdapter;
import com.chf.wanandroid.ui.utils.SnackBarUtil;
import com.chf.wanandroid.ui.widget.AutoRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 我的收藏
 *
 * @author 17919
 * @date 2018/4/8
 */

@SuppressLint("Registered")
public class MyCollectionActivity extends BaseToolBarActivity<MyCollectionPresenter> implements MyCollectionView, AutoRecyclerView.LoadMoreListener, OnRefreshListener {

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
    @BindView(R.id.main_layout)
    LinearLayout mMainLayout;

    private int page = 0;
    private MyCollectionAdapter myCollectionAdapter;
    private boolean canLoad;


    @Override
    public void initView() {
        refreshView.setOnRefreshListener(this);
        initRv();
        refreshView.autoRefresh();
    }

    private void initRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLoadMoreListener(this);
        myCollectionAdapter = new MyCollectionAdapter(R.layout.item_home_content, new ArrayList<MyCollectionBean.DatasBean>());
        recyclerView.setAdapter(myCollectionAdapter);
        myCollectionAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        myCollectionAdapter.isFirstOnly(false);
        myCollectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseWebViewActivity.actionStart(MyCollectionActivity.this, myCollectionAdapter.getData().get(position).getLink());
            }
        });

        myCollectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showMsg("取消收藏成功");
                presenter.unCollection(myCollectionAdapter.getData().get(position).getId(),myCollectionAdapter.getData().get(position).getOrigin());
            }
        });
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


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MyCollectionActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void loadMore() {
        if (!canLoad) {
            page += 1;
            presenter.getMyCollectionData(page);
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh();
        page = 0;
        if (myCollectionAdapter.getData().size() > 0) {
            myCollectionAdapter.getData().clear();
        }
        presenter.getMyCollectionData(page);
    }

    @Override
    public void showView(MyCollectionBean beans) {
        canLoad = beans.isOver();
        myCollectionAdapter.getData().addAll(beans.getDatas());
        myCollectionAdapter.notifyDataSetChanged();
    }

    @Override
    public void unCollectSuccess() {
        refreshView.autoRefresh();
    }

    @Override
    public void showErrorView(String errorMsg) {
        SnackBarUtil.showTipWithoutAction(mMainLayout,errorMsg);
    }


}

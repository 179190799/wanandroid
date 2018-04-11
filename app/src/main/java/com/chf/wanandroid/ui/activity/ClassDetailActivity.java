package com.chf.wanandroid.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chf.wanandroid.R;
import com.chf.wanandroid.base.BaseToolBarActivity;
import com.chf.wanandroid.mvp.model.bean.ArticleBean;
import com.chf.wanandroid.mvp.presenter.ClassDetailPresenter;
import com.chf.wanandroid.mvp.view.ClassDetailView;
import com.chf.wanandroid.ui.adapter.ClassDetailAdapter;
import com.chf.wanandroid.ui.utils.SnackBarUtil;
import com.chf.wanandroid.ui.widget.AutoRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 体系详情页
 * Created by Administrator on 2018/4/11.
 */

public class ClassDetailActivity extends BaseToolBarActivity<ClassDetailPresenter> implements ClassDetailView, OnRefreshListener, AutoRecyclerView.LoadMoreListener {

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

    @BindView(R.id.go_top_fab)
    FloatingActionButton mFab;

    private String title;
    private String id;
    private int page = 0;

    private ClassDetailAdapter mAdapter;
    private boolean isOver = false;

    @Override
    public void initView() {
        initBundle();
        initRv();
        refreshView.autoRefresh();
    }

    private void initRv() {
        refreshView.setOnRefreshListener(this);
        recyclerView.setLoadMoreListener(this);
        recyclerView.applyFloatingActionButton(mFab);
        mAdapter = new ClassDetailAdapter(R.layout.item_home_content, new ArrayList<ArticleBean.DatasBean>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);

        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
//        mAdapter.isFirstOnly(false);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseWebViewActivity.actionStart(mContext, mAdapter.getData().get(position).getLink());
            }
        });

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                presenter.onItemChildClick((ImageView) view, (ArticleBean.DatasBean) adapter.getData().get(position));
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.smoothScrollToPosition(0);
                    }
                });
            }
        });
    }
    

    private void initBundle() {
        if (this.getIntent() != null) {
            title = this.getIntent().getStringExtra("title");
            id = this.getIntent().getStringExtra("id");
            actionBar.setTitle(title);
        }
    }

    @Override
    public void showView(ArticleBean articleBean) {
        isOver = articleBean.isOver();
        mAdapter.getData().addAll(articleBean.getDatas());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorView(String msg) {
        SnackBarUtil.showTipWithoutAction(commonRefreshLayout, msg);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_class_detail;
    }

    @Override
    protected void initPresenter() {
        presenter = new ClassDetailPresenter(this, this);
        presenter.init();
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(canBack());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void loadMore() {
        if (!isOver) {
            page += 1;
            presenter.getDetailData(page, id);
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh();
        if (mAdapter.getData().size() > 0) {
            mAdapter.getData().clear();
        }
        page = 0;
        presenter.getDetailData(page, id);
    }

    public static void actionStart(Context context, String title, String cid) {
        Intent intent = new Intent(context, ClassDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("id", cid);
        context.startActivity(intent);
    }

}

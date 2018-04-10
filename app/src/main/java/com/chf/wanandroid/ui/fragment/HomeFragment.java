package com.chf.wanandroid.ui.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.chf.wanandroid.R;
import com.chf.wanandroid.base.BaseFragment;
import com.chf.wanandroid.mvp.model.HomeModel;
import com.chf.wanandroid.mvp.presenter.HomeFragmentPresenter;
import com.chf.wanandroid.mvp.view.HomeFragmentView;
import com.chf.wanandroid.ui.events.RefreshEvent;
import com.chf.wanandroid.ui.adapter.HomeAdapter;
import com.chf.wanandroid.ui.utils.ToastUtil;
import com.chf.wanandroid.ui.widget.AutoRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

/**
 * @author 17919
 * @date 2018/3/20
 */

public class HomeFragment extends BaseFragment<HomeFragmentPresenter> implements HomeFragmentView,
        OnLoadmoreListener, OnRefreshListener {

    @BindView(R.id.recycler_view)
    AutoRecyclerView recyclerView;
    @BindView(R.id.refresh_view)
    SmartRefreshLayout refreshView;
    @BindView(R.id.common_refresh_layout)
    LinearLayout commonRefreshLayout;
    @BindView(R.id.go_top_fab)
    FloatingActionButton mFab;


    private int page = 0;
    private HomeAdapter homeAdapter;
    private boolean over = false;
    private HomeModel homeModel = new HomeModel();

    @Override
    protected int provideViewLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter() {
        presenter = new HomeFragmentPresenter(getActivity(), this);
        presenter.init();
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        refreshView.post(new Runnable() {
            @Override
            public void run() {
                refreshView.autoRefresh();
            }
        });
        initRecyclerView();
    }

    @Override
    public void initRecyclerView() {
        homeAdapter = new HomeAdapter(homeModel, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(homeAdapter);
        recyclerView.applyFloatingActionButton(mFab);

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

        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.onItemClick(view, position, homeModel.articleBean.getDatas());
            }
        });

        refreshView.setOnRefreshListener(this);
        refreshView.setOnLoadmoreListener(this);

    }

    @Override
    public void showView(HomeModel model) {
        homeModel = model;
        homeAdapter.setData(homeModel);
    }

    @Override
    public void noLoadMore() {
        ToastUtil.showShort(getActivity(), "数据加载完毕");
        refreshView.setEnableLoadmore(false);
        over = true;
    }

    @Override
    public void loadMoreData(HomeModel model) {
        homeAdapter.loadMoreData(model);
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh();
        refreshlayout.setEnableLoadmore(true);
        presenter.getHomeData(page);
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        refreshlayout.finishLoadmore();
        if (!over) {
            page += 1;
            presenter.getHomeData(page);
        }
    }

    @Subscribe
    public void refreshEvent(RefreshEvent event) {
        if (event == null) {
            return;
        }

        if (event.type == 1) {
            refreshView.autoRefresh();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}

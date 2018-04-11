package com.chf.wanandroid.ui.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.chf.wanandroid.R;
import com.chf.wanandroid.base.BaseFragment;
import com.chf.wanandroid.mvp.model.bean.ClassBean;
import com.chf.wanandroid.mvp.presenter.ClassFragmentPresenter;
import com.chf.wanandroid.mvp.view.ClassFragmentView;
import com.chf.wanandroid.ui.adapter.ClassficationAdapter;
import com.chf.wanandroid.ui.widget.AutoRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 知识体系fragment
 * 
 * @author 17919
 * @date 2018/3/20
 */

public class ClassFragment extends BaseFragment<ClassFragmentPresenter> implements ClassFragmentView, OnRefreshListener {
    @BindView(R.id.recycler_view)
    AutoRecyclerView recyclerView;
    @BindView(R.id.refresh_view)
    SmartRefreshLayout refreshView;
    @BindView(R.id.common_refresh_layout)
    LinearLayout commonRefreshLayout;
    @BindView(R.id.class_go_top_fab)
    FloatingActionButton mFab;

    private List<ClassBean> mData = new ArrayList<>();
    private ClassficationAdapter mAdapter;

    @Override
    public void initView() {

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
        mAdapter = new ClassficationAdapter(getActivity(), mData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);
        recyclerView.applyFloatingActionButton(mFab);

        refreshView.setOnRefreshListener(this);
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

    @Override
    public void showErrorView() {

    }

    @Override
    protected int provideViewLayout() {
        return R.layout.fragment_class_fication;
    }

    @Override
    protected void initPresenter() {
        presenter = new ClassFragmentPresenter(getActivity(), this);
        presenter.init();
    }

    @Override
    public void showView(List<ClassBean> classBeans) {
        mAdapter.setData(classBeans);
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh();
        presenter.getClassFicationData();
    }
}

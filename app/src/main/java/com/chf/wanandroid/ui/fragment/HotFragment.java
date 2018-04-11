package com.chf.wanandroid.ui.fragment;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chf.wanandroid.R;
import com.chf.wanandroid.base.BaseFragment;
import com.chf.wanandroid.mvp.model.bean.HotKeyWordBean;
import com.chf.wanandroid.mvp.model.bean.HotWebBean;
import com.chf.wanandroid.mvp.presenter.HotFragmentPresenter;
import com.chf.wanandroid.mvp.view.HotFragmentView;
import com.chf.wanandroid.ui.adapter.HotKeyAdapter;
import com.chf.wanandroid.ui.adapter.HotWebAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 热门fragment
 *
 * @author 17919
 * @date 2018/3/20
 */

public class HotFragment extends BaseFragment<HotFragmentPresenter> implements HotFragmentView {

    @BindView(R.id.hot_web_rv)
    RecyclerView mWebRv;
    @BindView(R.id.hot_key_rv)
    RecyclerView mKeyRv;
    @BindView(R.id.hot_sv)
    NestedScrollView mSv;

    @BindView(R.id.hot_content_layout)
    LinearLayout mContentLayout;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.loading_ll)
    LinearLayout mLoadingLl;
    @BindView(R.id.common_loading_layout)
    LinearLayout mCommonLoadingLayout;

    private List<HotWebBean> webBeans = new ArrayList<>();
    private List<HotKeyWordBean> keyWordBeans = new ArrayList<>();
    private HotWebAdapter hotWebAdapter;
    private HotKeyAdapter hotKeyAdapter;

    @Override
    public void initView() {
        initRecyclerView();
        mWebRv.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getHotWebData();
                presenter.getHotKeyData();
            }
        }, 1000);

    }

    @Override
    public void initRecyclerView() {
        mWebRv.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        hotWebAdapter = new HotWebAdapter(R.layout.item_hot_label, webBeans);
        mWebRv.setAdapter(hotWebAdapter);
        hotWebAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                presenter.onItemClick(view, position, webBeans);
            }
        });

        mKeyRv.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        hotKeyAdapter = new HotKeyAdapter(R.layout.item_hot_label, keyWordBeans);
        mKeyRv.setAdapter(hotKeyAdapter);
        hotKeyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showMsg(keyWordBeans.get(position).getName());
            }
        });


    }

    @Override
    protected int provideViewLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initPresenter() {
        presenter = new HotFragmentPresenter(getActivity(), this);
        presenter.init();
    }

    @Override
    public void showHotKeyView(List<HotKeyWordBean> keyWordBeans) {
        hotKeyAdapter.getData().addAll(keyWordBeans);
        hotKeyAdapter.notifyDataSetChanged();
    }

    @Override
    public void showHotWebView(List<HotWebBean> webBeans) {
        hotWebAdapter.getData().addAll(webBeans);
        hotWebAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        if (mContentLayout.getVisibility() == View.VISIBLE) {
            mContentLayout.setVisibility(View.GONE);
        }
        if (mCommonLoadingLayout.getVisibility() == View.GONE) {
            mCommonLoadingLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (mContentLayout.getVisibility() == View.GONE) {
            mContentLayout.setVisibility(View.VISIBLE);
        }

        if (mCommonLoadingLayout.getVisibility() == View.VISIBLE) {
            mCommonLoadingLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void showErrorView() {

    }


}

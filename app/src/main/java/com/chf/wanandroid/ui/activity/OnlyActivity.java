package com.chf.wanandroid.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;

import com.chf.wanandroid.R;
import com.chf.wanandroid.base.BaseToolBarActivity;
import com.chf.wanandroid.mvp.presenter.OnlyPresenter;
import com.chf.wanandroid.mvp.view.OnlyView;

/**
 * Created by THINK on 2018/4/13.
 */

public class OnlyActivity extends BaseToolBarActivity<OnlyPresenter> implements OnlyView {
    @Override
    public void initView() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_only;
    }

    @Override
    protected void initPresenter() {
        presenter = new OnlyPresenter(this, this);
        presenter.init();
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        actionBar = getSupportActionBar();
        if (actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(canBack());
            actionBar.setTitle("Only");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, OnlyActivity.class);
        context.startActivity(intent);
    }
}

package com.chf.wanandroid.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;

import com.chf.wanandroid.R;
import com.chf.wanandroid.base.BaseToolBarActivity;
import com.chf.wanandroid.mvp.presenter.AboutPresenter;
import com.chf.wanandroid.mvp.view.AboutView;

/**
 * Created by THINK on 2018/4/13.
 */

public class AboutActivity extends BaseToolBarActivity<AboutPresenter> implements AboutView {
    @Override
    public void initView() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initPresenter() {
        presenter = new AboutPresenter(this, this);
        presenter.init();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(canBack());
            actionBar.setTitle("关于");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}

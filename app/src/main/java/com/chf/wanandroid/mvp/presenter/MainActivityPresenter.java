package com.chf.wanandroid.mvp.presenter;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.TextView;

import com.chf.wanandroid.base.BasePresenter;
import com.chf.wanandroid.mvp.view.MainActivityView;
import com.chf.wanandroid.ui.Events.RefreshEvent;

/**
 * MainActivity Presenter
 *
 * @author 17919
 * @date 2018/3/19
 */

public class MainActivityPresenter extends BasePresenter<MainActivityView> {
    public MainActivityPresenter(Context context, MainActivityView iView) {
        super(context, iView);
    }


    public DrawerLayout.SimpleDrawerListener mSimpleDrawerListener = new DrawerLayout.SimpleDrawerListener() {
        @Override
        public void onDrawerOpened(View drawerView) {
            //档DrawerLayout打开时，让整体DrawerLayout布局可以响应点击事件
            drawerView.setClickable(true);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
        }
    };

    public void refreshLayout(RefreshEvent event, TextView drawQuitLoginTv) {
        if (event == null) {
            return;
        }

        if (event.type == 1) {
            drawQuitLoginTv.setText("退出登录");
        }
    }
}

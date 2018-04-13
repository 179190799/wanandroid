package com.chf.wanandroid.mvp.presenter;

import android.content.Context;

import com.chf.wanandroid.base.BasePresenter;
import com.chf.wanandroid.mvp.view.AboutView;

/**
 * Created by THINK on 2018/4/13.
 */

public class AboutPresenter extends BasePresenter<AboutView>{
    
    public AboutPresenter(Context context, AboutView iView) {
        super(context, iView);
    }
}

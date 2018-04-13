package com.chf.wanandroid.mvp.presenter;

import android.content.Context;

import com.chf.wanandroid.base.BasePresenter;
import com.chf.wanandroid.mvp.view.OnlyView;

/**
 * Created by THINK on 2018/4/13.
 */

public class OnlyPresenter extends BasePresenter<OnlyView>{
    
    public OnlyPresenter(Context context, OnlyView iView) {
        super(context, iView);
    }
}

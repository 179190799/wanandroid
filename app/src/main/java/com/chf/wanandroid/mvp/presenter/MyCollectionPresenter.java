package com.chf.wanandroid.mvp.presenter;

import android.content.Context;

import com.chf.wanandroid.base.BasePresenter;
import com.chf.wanandroid.mvp.view.MyCollectionView;

/**
 * @author 17919
 * @date 2018/4/8
 */

public class MyCollectionPresenter extends BasePresenter<MyCollectionView> {

    public MyCollectionPresenter(Context context, MyCollectionView iView) {
        super(context, iView);
    }
}

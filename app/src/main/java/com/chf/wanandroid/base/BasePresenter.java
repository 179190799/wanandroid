package com.chf.wanandroid.base;

import android.content.Context;

import io.reactivex.disposables.Disposable;

/**
 * p 层基类
 *
 * @author bob
 * @date 17-5-1
 */

public abstract class BasePresenter<T extends BaseView> {
    protected Disposable disposable;
    protected Disposable disposable1;
    protected Context mContext;
    protected T iView;

    public BasePresenter(Context context, T iView) {
        this.mContext = context;
        this.iView = iView;
    }

    public void init() {
        iView.initView();
    }

    public void release() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        if (disposable1 != null && !disposable1.isDisposed()) {
            disposable1.dispose();
        }
    }
}

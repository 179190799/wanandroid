package com.chf.wanandroid.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.chf.wanandroid.base.BasePresenter;
import com.chf.wanandroid.http.BaseCompose;
import com.chf.wanandroid.http.BaseResponse;
import com.chf.wanandroid.http.ErrorConsumer;
import com.chf.wanandroid.http.OiApiManager;
import com.chf.wanandroid.mvp.model.bean.MyCollectionBean;
import com.chf.wanandroid.mvp.view.MyCollectionView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 17919
 * @date 2018/4/8
 */

public class MyCollectionPresenter extends BasePresenter<MyCollectionView> {

    public MyCollectionPresenter(Context context, MyCollectionView iView) {
        super(context, iView);
    }

    public void getMyCollectionData(int page) {
      disposable=  OiApiManager.getApiServie().getMyCollectionData(page)
                .compose(new BaseCompose<MyCollectionBean>())
                .subscribe(new Consumer<MyCollectionBean>() {
                    @Override
                    public void accept(MyCollectionBean beans) throws Exception {
                        iView.showView(beans);
                    }
                }, new ErrorConsumer(mContext));
    }

    public void unCollection(int id,String originId) {
        if (TextUtils.isEmpty(originId)) {
            originId = "-1";
        }
        disposable1 = OiApiManager.getApiServie().unCollectionFromCollect(id,originId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse baseResponse) throws Exception {
                        if (baseResponse.errorCode == 0) {
                            iView.unCollectSuccess();
                        } else {
                            iView.showErrorView(baseResponse.errorMsg);
                        }
                    }
                }, new ErrorConsumer(mContext));
    }
}

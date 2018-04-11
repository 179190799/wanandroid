package com.chf.wanandroid.mvp.presenter;

import android.content.Context;
import android.widget.ImageView;

import com.chf.wanandroid.R;
import com.chf.wanandroid.base.BasePresenter;
import com.chf.wanandroid.http.BaseCompose;
import com.chf.wanandroid.http.BaseResponse;
import com.chf.wanandroid.http.ErrorConsumer;
import com.chf.wanandroid.http.OiApiManager;
import com.chf.wanandroid.mvp.model.bean.ArticleBean;
import com.chf.wanandroid.mvp.view.ClassDetailView;
import com.chf.wanandroid.ui.utils.StringUtils;
import com.chf.wanandroid.ui.utils.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/11.
 */

public class ClassDetailPresenter extends BasePresenter<ClassDetailView> {

    public ClassDetailPresenter(Context context, ClassDetailView iView) {
        super(context, iView);
    }

    public void getDetailData(int page, String id) {
        OiApiManager.getApiServie().getClassDetailData(page, id)
                .compose(new BaseCompose<ArticleBean>())
                .subscribe(new Consumer<ArticleBean>() {
                    @Override
                    public void accept(ArticleBean articleBean) throws Exception {
                        iView.showView(articleBean);
                    }
                }, new ErrorConsumer(mContext) {
                    @Override
                    public void onError(Throwable throwable) throws Exception {
                        super.onError(throwable);
                        iView.showErrorView(throwable.getMessage());
                    }
                });
    }

    /**
     * 点击收藏按钮
     * @param view
     * @param datasBean
     */
    public void onItemChildClick(final ImageView view, ArticleBean.DatasBean datasBean) {
        //                    检测是否登录
        if (!StringUtils.isLogin(mContext)) {
            ToastUtil.showShort(mContext, "请先登录");
            return;
        }
        if (datasBean.isCollect()) {
//                        取消收藏
            OiApiManager.getApiServie().unCollectionFromArticle(datasBean.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<BaseResponse>() {
                        @Override
                        public void accept(BaseResponse baseResponse) throws Exception {
                            if (baseResponse.errorCode == 0) {
                                ToastUtil.showShort(mContext, "取消收藏成功");
                                view.setImageResource(R.drawable.icon_collection_gray);
                            } else {
                                ToastUtil.showShort(mContext, baseResponse.errorMsg);
                            }

                        }
                    }, new ErrorConsumer(mContext));


        } else {
//                        收藏
            OiApiManager.getApiServie().collectionArticle(datasBean.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<BaseResponse>() {
                        @Override
                        public void accept(BaseResponse baseResponse) throws Exception {
                            if (baseResponse.errorCode == 0) {
                                ToastUtil.showShort(mContext, "收藏成功");
                                view.setImageResource(R.drawable.icon_collection);
                            } else {
                                ToastUtil.showShort(mContext, baseResponse.errorMsg);
                            }
                        }
                    }, new ErrorConsumer(mContext));

        }
    }
}

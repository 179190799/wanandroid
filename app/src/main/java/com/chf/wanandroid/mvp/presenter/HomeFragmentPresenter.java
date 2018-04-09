package com.chf.wanandroid.mvp.presenter;

import android.content.Context;
import android.view.View;

import com.chf.wanandroid.base.BasePresenter;
import com.chf.wanandroid.http.BaseResponse;
import com.chf.wanandroid.http.ErrorConsumer;
import com.chf.wanandroid.http.OiApiManager;
import com.chf.wanandroid.mvp.model.HomeModel;
import com.chf.wanandroid.mvp.model.bean.ArticleBean;
import com.chf.wanandroid.mvp.model.bean.BannerBean;
import com.chf.wanandroid.mvp.view.HomeFragmentView;
import com.chf.wanandroid.ui.activity.BaseWebViewActivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * @author 17919
 * @date 2018/3/20
 */

public class HomeFragmentPresenter extends BasePresenter<HomeFragmentView> {
    public HomeFragmentPresenter(Context context, HomeFragmentView iView) {
        super(context, iView);
    }

    /**
     * 获取首页数据
     *
     * @param page
     */
    public void getHomeData(final int page) {
        Observable.zip(OiApiManager.getApiServie().getBannerData(), OiApiManager.getApiServie().getArticleData(page)
                , new BiFunction<BaseResponse<List<BannerBean>>, BaseResponse<ArticleBean>, HomeModel>() {
                    @Override
                    public HomeModel apply(BaseResponse<List<BannerBean>> listBaseResponse, BaseResponse<ArticleBean> articleBeanBaseResponse) throws Exception {
                        HomeModel homeModel = new HomeModel();
                        homeModel.articleBean = articleBeanBaseResponse.data;
                        homeModel.bannerBeans = listBaseResponse.data;
                        return homeModel;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeModel>() {
                    @Override
                    public void accept(HomeModel homeModel) throws Exception {
                        if (page == 0) {
                            iView.showView(homeModel);
                        } else {
                            iView.loadMoreData(homeModel);
                        }

                        if (homeModel.articleBean.isOver()) {
                            iView.noLoadMore();
                        }
                    }
                }, new ErrorConsumer(mContext));
    }


    public void onItemClick(View view, int position, List<ArticleBean.DatasBean> articleBean) {
        BaseWebViewActivity.actionStart(mContext,articleBean.get(position).getLink());
    }
}

package com.chf.wanandroid.http;

import android.app.Application;

import com.chf.wanandroid.base.BaseApplication;

/**
 * 接口API 方法
 *
 * @author DuoNuo
 * @date 2017/9/21
 */

public class ApiManager {

    private ApiService apiService;
    private Application application;

    private ApiManager(ApiService apiService, Application application) {
        this.apiService = apiService;
        this.application = application;
    }

    private static class OiRetrofitUtilsHolder {
        private static ApiManager oiApiManager = new ApiManager(OiApiManager.getApiServie(), BaseApplication.get());
    }

    public static ApiManager getInstace() {
        return OiRetrofitUtilsHolder.oiApiManager;
    }

//    /**
//     * 获取首页文章列表
//     * @param page
//     * @param simpleCallback
//     */
//    public void getArticleData(int page, OiSimpleCallback<ArticleBean> simpleCallback) {
//        apiService.getArticleData(page)
//                .compose(new NetTransformer<ArticleBean>())
//                .subscribe(new ExceptionSubscriber<ArticleBean>(simpleCallback, application));
//
//    }
//
//    /**
//     * 获取首页Banner图片
//     */
//    public void getBannerData(OiSimpleCallback<BannerBean> simpleCallback) {
//        apiService.getBannerData()
//                .compose(new NetTransformer<BannerBean>())
//                .subscribe(new ExceptionSubscriber<BannerBean>(simpleCallback,application));
//    }

}

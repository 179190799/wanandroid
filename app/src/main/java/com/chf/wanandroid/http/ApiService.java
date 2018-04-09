package com.chf.wanandroid.http;

import com.chf.wanandroid.mvp.model.bean.ArticleBean;
import com.chf.wanandroid.mvp.model.bean.BannerBean;
import com.chf.wanandroid.mvp.model.bean.ClassBean;
import com.chf.wanandroid.mvp.model.bean.HotKeyWordBean;
import com.chf.wanandroid.mvp.model.bean.HotWebBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 接口API 文档
 * Created by DuoNuo on 2017/9/21
 *
 * @author 17919
 */
public interface ApiService {

    /**
     * 获取首页文章列表数据
     *
     * @param page 页码
     * @return
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<ArticleBean>> getArticleData(@Path("page") int page);

    /**
     * 获取首页Banner图片
     *
     * @return
     */
    @GET("banner/json")
    Observable<BaseResponse<List<BannerBean>>> getBannerData();

    /**
     * 获取知识体系
     *
     * @return
     */
    @GET("/tree/json")
    Observable<BaseResponse<List<ClassBean>>> getClassData();

    /**
     * 热门网址
     * @return
     */
    @GET("friend/json")
    Observable<BaseResponse<List<HotWebBean>>> getHotWebData();

    /**
     * 热门搜索词
     * @return
     */
    @GET("hotkey/json")
    Observable<BaseResponse<List<HotKeyWordBean>>> getHotKeyData();


}

package com.chf.wanandroid.http;

import com.chf.wanandroid.mvp.model.bean.ArticleBean;
import com.chf.wanandroid.mvp.model.bean.BannerBean;
import com.chf.wanandroid.mvp.model.bean.ClassBean;
import com.chf.wanandroid.mvp.model.bean.HotKeyWordBean;
import com.chf.wanandroid.mvp.model.bean.HotWebBean;
import com.chf.wanandroid.mvp.model.bean.MyCollectionBean;
import com.chf.wanandroid.mvp.model.bean.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    
//    @GET("/article/list/0/json?cid=60")
    

    /**
     * 热门网址
     *
     * @return
     */
    @GET("friend/json")
    Observable<BaseResponse<List<HotWebBean>>> getHotWebData();

    /**
     * 热门搜索词
     *
     * @return
     */
    @GET("hotkey/json")
    Observable<BaseResponse<List<HotKeyWordBean>>> getHotKeyData();

    /**
     * 登录
     *
     * @param userName
     * @param pwd
     * @return
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<UserBean>> goLogin(@Field("username") String userName, @Field("password") String pwd);

    /**
     * 注册
     *
     * @param userName
     * @param pwd
     * @param rePwd
     * @return
     */
    @POST("user/register")
    @FormUrlEncoded
    Observable<BaseResponse<UserBean>> register(@Field("username") String userName,
                                                @Field("password") String pwd,
                                                @Field("repassword") String rePwd);

    /**
     * 收藏列表
     *
     * @param page
     * @return
     */
    @GET("lg/collect/list/{page}/json")
    Observable<BaseResponse<MyCollectionBean>> getMyCollectionData(@Path("page") int page);

    /**
     * 收藏
     *
     * @param id 文章id
     * @return
     */
    @POST("lg/collect/{id}/json")
    Observable<BaseResponse> collectionArticle(@Path("id") int id);


    /**
     * 文章列表取消收藏
     *
     * @param id
     * @return
     */
    @POST("lg/uncollect_originId/{id}/json")
    Observable<BaseResponse> unCollectionFromArticle(@Path("id") int id);


    /**
     * 收藏列表取消收藏
     *
     * @param id
     * @return
     */
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    Observable<BaseResponse> unCollectionFromCollect(@Path("id") int id,@Field("originId")String originId);

}

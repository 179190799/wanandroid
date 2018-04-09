package com.chf.wanandroid.http;

import com.chf.wanandroid.base.BaseApplication;
import com.chf.wanandroid.http.interceptor.AddCookiesInterceptor;
import com.chf.wanandroid.http.interceptor.SaveCookiesInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 域名请求  根域名
 *
 * @author DuoNuo
 * @date 2017/12/15
 */

public class OiApiManager {

    public static final String BaseUrl = "http://www.wanandroid.com/";
    public static final boolean DEBUG = Boolean.parseBoolean("true");

    private OiApiManager() {
    }

    private static class OiRetrofitUtilsHolder {
        private static OiApiManager apiManager = new OiApiManager();
    }


    public OiApiManager getInstace() {
        return OiRetrofitUtilsHolder.apiManager;
    }

    public static ApiService getApiServie() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
//        打印日志
        if (DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpBuilder.addInterceptor(httpLoggingInterceptor);
        }

        okHttpBuilder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(new SaveCookiesInterceptor(BaseApplication.get()))
                .addInterceptor(new AddCookiesInterceptor(BaseApplication.get()))
//                错误重连
                .retryOnConnectionFailure(false)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();

        retrofitBuilder.client(okHttpBuilder.build())
                .baseUrl(BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return retrofitBuilder.build().create(ApiService.class);
    }
}

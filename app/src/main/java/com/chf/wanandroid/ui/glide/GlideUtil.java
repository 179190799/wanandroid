package com.chf.wanandroid.ui.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chf.wanandroid.R;

/**
 * glideUtil
 *
 * @author 17919
 * @date 2018/3/28
 */

public class GlideUtil {

    /**
     * glide 选项设置
     */
    public static RequestOptions options = new RequestOptions()
//            占位符
            .placeholder(R.mipmap.ic_launcher)
//            加载错误显示的图片
            .error(R.mipmap.ic_launcher)
//            优先权
//            .priority(Priority.HIGH)
//            缓存图片
            .diskCacheStrategy(DiskCacheStrategy.ALL);


    /**
     * 加载本地资源图片
     *
     * @param context
     * @param resId
     * @param imageView
     */
    public static void displayResImage(Context context, int resId, ImageView imageView) {
        GlideApp.with(context)
                .applyDefaultRequestOptions(options.centerCrop())
                .load(resId)
                .into(imageView);
    }

    /**
     * 加载本地资源图片,以圆形图片加载
     *
     * @param context
     * @param resId
     * @param imageView
     */
    public static void displayResImageFromCircle(Context context, int resId, ImageView imageView) {
        GlideApp.with(context)
                .applyDefaultRequestOptions(options.centerCrop().circleCrop())
                .load(resId)
                .into(imageView);
    }

    /**
     * 加载本地图片,圆角加载
     *
     * @param context
     * @param resId
     * @param imageView
     * @param corners   此处为圆角px值
     */
    public static void displayResImageFromCorners(Context context, int resId, ImageView imageView, int corners) {
        GlideApp.with(context)
                .applyDefaultRequestOptions(options
                        .centerCrop()
                        .transform(new RoundedCorners(corners)))
                .load(resId)
                .into(imageView);
    }

    /**
     * 加载网络资源图片
     *
     * @param context
     * @param resId
     * @param imageView
     */
    public static void displayImage(Context context, String resId, ImageView imageView) {
        GlideApp.with(context)
                .applyDefaultRequestOptions(options)
                .load(resId)
                .into(imageView);
    }

    /**
     * 加载网络资源图片,以圆形图片加载
     *
     * @param context
     * @param resId
     * @param imageView
     */
    public static void displayImageFromCircle(Context context, String resId, ImageView imageView) {
        GlideApp.with(context)
                .applyDefaultRequestOptions(options.circleCrop())
                .load(resId)
                .into(imageView);
    }

    /**
     * 加载网络资源图片,圆角加载
     *
     * @param context
     * @param resId
     * @param imageView
     * @param corners   此处为圆角px值
     */
    public static void displayImageFromCorners(Context context, String resId, ImageView imageView, int corners) {
        GlideApp.with(context)
                .applyDefaultRequestOptions(options
                        .centerCrop()
                        .transform(new RoundedCorners(corners)))
                .load(resId)
                .into(imageView);
    }




}

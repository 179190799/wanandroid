package com.chf.wanandroid.ui.utils;

import android.content.Context;

import com.chf.wanandroid.ui.base.BaseConfig;


/**
 * 
 *
 * @author Administrator
 * @date 2018/3/8
 */

public class StringUtils {


    /**
     * 是否登陆
     * @param context
     * @return
     */
    public static boolean isLogin(Context context) {
        return (boolean) SharedPreferencesUtil.get(context, BaseConfig.IS_LOGIN, false);
    }

    /**
     * 获取存储在SharedPreferences 中的String数据
     * @param context
     * @param key
     * @return
     */
    public  static String getSpString(Context context,String key) {
        return (String) SharedPreferencesUtil.get(context, key, "");
    }


    /**
     * 获取存储在SharedPreferences 中的int数据
     * @param context
     * @param key
     * @return
     */
    public static int getSpInt(Context context,String key) {
        return (int) SharedPreferencesUtil.get(context, key, 0);
    }


    /**
     * 获取存储在SharedPreferences 中的boolean数据
     * @param context
     * @param key
     * @return
     */
    public static boolean getSpBoolean(Context context,String key) {
        return (Boolean) SharedPreferencesUtil.get(context, key, false);
    }

    /**
     * 获取存储在SharedPreferences 中的floatg数据
     * @param context
     * @param key
     * @return
     */
    public static float getSpFloat(Context context,String key) {
        return (float) SharedPreferencesUtil.get(context, key, 0f);
    }
}

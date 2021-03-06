package com.chf.wanandroid.ui.utils;

import android.content.Context;
import android.util.TypedValue;

public class DensityUtil {  
	  
    /** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
    
    public static int px2sp(Context context, float pxValue) {  
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (pxValue / fontScale + 0.5f);  
    }  
  
    /** 
     * 将sp值转换为px值，保证文字大小不变 
     *  
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity） 
     * @return 
     */  
    public static int sp2px(Context context, float spValue) {  
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (spValue * fontScale + 0.5f);  
    }

    /**
     * 根据dip值转化成px值
     *
     * @param context
     * @param dip
     * @return
     */
		public static int dipToPix(Context context, int dip) {
			int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, context.getResources().getDisplayMetrics());
			return size;
		}

    /**
     * 获取手机屏幕的宽
     *
     * @param context
     * @return
     */
    public static int windowsWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取手机屏幕的高
     *
     * @param context
     * @return
     */
    public static int windowsheight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}  
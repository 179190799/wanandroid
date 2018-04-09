package com.chf.wanandroid.ui.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

/**
 * Created by DuoNuo on 2017/12/8.
 */

public class OiViewUtils {

    public static void setDrawables(Context context, TextView target, int mipmap){
        Drawable drawable = ContextCompat.getDrawable(context, mipmap);
        drawable.setBounds(0,0,drawable.getMinimumWidth(), drawable.getMinimumHeight());
        target.setCompoundDrawables(null,null,drawable,null);
    }
}

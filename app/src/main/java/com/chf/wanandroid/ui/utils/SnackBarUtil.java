package com.chf.wanandroid.ui.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 *
 * @author Administrator
 * @date 2018/1/6
 */

public class SnackBarUtil {


    public static void showTipWithAction(View view, String tipText, String actionText, View.OnClickListener listener) {
        Snackbar.make(view, tipText, Snackbar.LENGTH_LONG).setAction(actionText, listener).show();
    }

    public static void showTipWithAction(View view, String tipText, String actionText, View.OnClickListener listener, int duration) {
        Snackbar.make(view, tipText, duration).setAction(actionText, listener).show();
    }

    public static void showTipWithoutAction(View view, String tipText) {
        Snackbar.make(view, tipText,Snackbar.LENGTH_SHORT).show();
    }
}

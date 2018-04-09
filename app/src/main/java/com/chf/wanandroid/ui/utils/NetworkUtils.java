package com.chf.wanandroid.ui.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

/**
 * 网络状态
 *
 * @author owner
 */
public class NetworkUtils {

    public static boolean isAvailable(Context context) {
        boolean flag = false;
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connManager==null){
            return false;
        }

        //Wifi
        State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (state == State.CONNECTED) {
            return true;
        }

        //3G
        state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if (state == State.CONNECTED) {
            return true;
        }

        return flag;
    }

}
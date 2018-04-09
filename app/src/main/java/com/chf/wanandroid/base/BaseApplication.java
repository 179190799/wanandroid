package com.chf.wanandroid.base;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.multidex.MultiDexApplication;

import com.chf.wanandroid.ui.service.MyIntentService;
import com.chf.wanandroid.ui.service.MyPushService;
import com.igexin.sdk.PushManager;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * 基本全局设置
 *
 * @author Administrator
 * @date 2018/1/4
 */

public class BaseApplication extends MultiDexApplication {



    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //加载时和刷新时禁止一切手势操作
                layout.setDisableContentWhenLoading(true);
                layout.setDisableContentWhenRefresh(true);
                //越界回弹
                layout.setEnableOverScrollDrag(true);
                return new MaterialHeader(mContext);
                //.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
    }

    public static BaseApplication get() {
        return baseApplication;
    }
    private static Context mContext;
    private static BaseApplication baseApplication;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        mContext = getApplicationContext();
        //解决7.0拍照崩溃问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        /**
         * 初始化ZXing
         */
        ZXingLibrary.initDisplayOpinion(this);

        PushManager.getInstance().initialize(this.getApplicationContext(), MyPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), MyIntentService.class);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

}

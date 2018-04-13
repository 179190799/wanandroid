package com.chf.wanandroid.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.chf.wanandroid.R;
import com.chf.wanandroid.http.BaseCompose;
import com.chf.wanandroid.http.ErrorConsumer;
import com.chf.wanandroid.http.OiApiManager;
import com.chf.wanandroid.mvp.model.bean.UserBean;
import com.chf.wanandroid.ui.base.BaseConfig;
import com.chf.wanandroid.ui.utils.SharedPreferencesUtil;
import com.chf.wanandroid.ui.utils.StringUtils;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 欢迎页
 *
 * @author 17919
 * @date 2018/4/1
 */

@SuppressLint("Registered")
public class WelcomeActivity extends AppCompatActivity {
    private Disposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        先隐藏标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome);

        if (!TextUtils.isEmpty(StringUtils.getSpString(this, BaseConfig.USER_NAME))) {
            disposable = OiApiManager.getApiServie().goLogin(StringUtils.getSpString(this, BaseConfig.USER_NAME),
                    StringUtils.getSpString(this, BaseConfig.USER_PWD))
                    .compose(new BaseCompose<UserBean>())
                    .subscribe(new Consumer<UserBean>() {
                        @Override
                        public void accept(UserBean userBean) throws Exception {
                            SharedPreferencesUtil.put(WelcomeActivity.this, BaseConfig.IS_LOGIN, true);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    MainActivity.actionStar(WelcomeActivity.this);
                                    finish();
                                }
                            }, 2000);

                        }
                    }, new ErrorConsumer(this) {
                        @Override
                        public void onError(Throwable throwable) throws Exception {
                            super.onError(throwable);
                        }
                    });
            return;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.actionStar(WelcomeActivity.this);
                finish();
            }
        }, 2000);

    }
}

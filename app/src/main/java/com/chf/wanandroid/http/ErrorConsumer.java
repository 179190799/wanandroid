package com.chf.wanandroid.http;

import android.content.Context;
import android.util.Log;

import com.chf.wanandroid.ui.utils.NetworkUtils;
import com.chf.wanandroid.ui.utils.ToastUtil;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

/**
 * 对异常进行统一处理
 *
 * @author Created by hf  2018-03-26 11:22
 * @version 1.0
 * @des
 * @版本 $Rev: 4882 $
 * @change $Author: wwei $  $Date: 2018-03-26 20:48:15 +0800 (周一, 2018-03-26) $
 * @des ${TODO}
 */
public class ErrorConsumer implements Consumer<Throwable> {

    private Context mContext;

    public ErrorConsumer(Context context) {
        mContext = context;
    }

    @Override
    public void accept(Throwable e) throws Exception {

        if (!NetworkUtils.isAvailable(mContext)) {
            ToastUtil.showShort(mContext,"网络连接中断");
            onError(e);
            return;
        }

        e.printStackTrace();

        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;

            int code = httpException.code();

            if (code >= 400 && code <= 417) {
                ToastUtil.showShort(mContext,code + "访问地址异常，请稍后重试！");
            } else if (code >= 500 && code <= 505) {
                ToastUtil.showShort(mContext,code + "服务器繁忙，请稍后重试");
            } else {
                ToastUtil.showShort(mContext,code + "网络连接异常");
            }

        } else if (e instanceof RuntimeException) {

            ToastUtil.showShort(mContext,"运行时异常");

        } else if (e instanceof SocketTimeoutException || e instanceof ConnectException) {

            ToastUtil.showShort(mContext,"网络连接超时，请稍后重试！");

        } else if (e instanceof JSONException) {

            ToastUtil.showShort(mContext,"数据解析异常");

        } else if (e instanceof UnknownHostException) {

            ToastUtil.showShort(mContext,"访问地址异常，请稍后重试！");

        } else {
            ToastUtil.showShort(mContext,"未知错误");
        }

        onError(e);
    }

    public void onError(Throwable throwable) throws Exception {
        Log.e("error", throwable.getMessage());
    }
}

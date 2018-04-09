package com.chf.wanandroid.http;

import android.app.Application;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;

/**
 * @author 17919
 */
public class ExceptionSubscriber<T> extends DisposableObserver<T> {

    private SimpleCallback<T> simpleCallback;
    private Application application;

    public ExceptionSubscriber(SimpleCallback<T> simpleCallback, Application application) {
        this.simpleCallback = simpleCallback;
        this.application = application;
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(application, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(application, "网络超时,请重新连接", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(application, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (simpleCallback != null){
            simpleCallback.onComplete();
        }
    }

    @Override
    public void onComplete() {
        if (simpleCallback != null){
            simpleCallback.onComplete();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (simpleCallback != null){
            simpleCallback.onStart();
        }
    }

    @Override
    public void onNext(T t) {
        if (simpleCallback != null){
            simpleCallback.onNext(t);
        }
    }
}

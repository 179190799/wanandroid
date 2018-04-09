package com.chf.wanandroid.http;

/**
 *
 * @author DuoNuo
 * @date 2017/9/21
 */
public interface SimpleCallback<T> {
    void onStart();
    void onNext(T t);
    void onComplete();
}

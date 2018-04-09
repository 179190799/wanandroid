package com.chf.wanandroid.http;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 复用
 *
 * @author 17919
 * @date 2018/3/29
 */

public class BaseCompose<T> implements ObservableTransformer<BaseResponse<T>, T> {

    @Override
    public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
        return upstream
                .flatMap(new Function<BaseResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(BaseResponse<T> response) throws Exception {
                        if (response.errorCode != 0) {
                            return Observable.error(new Throwable(response.errorMsg));
                        }
                        if (response.data == null) {
                            return Observable.error(new Throwable("data is null"));
                        }
                        return Observable.just(response.data);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

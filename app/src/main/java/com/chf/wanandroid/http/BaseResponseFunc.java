package com.chf.wanandroid.http;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author DuoNuo
 * @date 2017/9/21
 */

public class BaseResponseFunc<T> implements Function<BaseResponse<T>, Observable<T>> {

    @Override
    public Observable<T> apply(@NonNull BaseResponse<T> tBaseResponse) throws Exception {
        if (tBaseResponse.errorCode != 0) {
            return Observable.error(new Throwable(tBaseResponse.errorMsg));
        } else {
            if (tBaseResponse.data != null) {
                return Observable.just(tBaseResponse.data);
            } else {
                return (Observable<T>) Observable.just("");
            }
        }
    }

}

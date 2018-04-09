package com.chf.wanandroid.ui.utils;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 *
 *
 * @author DuoNuo
 * @date 2017/12/24
 */

public class OiBus {

    private OiBus(){}

    private static class OiBusHolder {
        private static final OiBus oiBus = new OiBus();
    }

    public static OiBus getInstanceFromHolder() {
        return OiBusHolder.oiBus;
    }

    private PublishSubject<Object> bus = PublishSubject.create();

    public void send(Object o){
        bus.onNext(o);
    }

    public Observable<Object> getObservable(){
        return bus;
    }

}

package com.chf.wanandroid.mvp.presenter;

import android.content.Context;

import com.chf.wanandroid.base.BasePresenter;
import com.chf.wanandroid.http.BaseCompose;
import com.chf.wanandroid.http.ErrorConsumer;
import com.chf.wanandroid.http.OiApiManager;
import com.chf.wanandroid.mvp.model.bean.ClassBean;
import com.chf.wanandroid.mvp.view.ClassFragmentView;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * @author 17919
 * @date 2018/3/20
 */

public class ClassFragmentPresenter extends BasePresenter<ClassFragmentView> {
    public ClassFragmentPresenter(Context context, ClassFragmentView iView) {
        super(context, iView);
    }

    /**
     * 获取知识体系数据
     */
    public void getClassFicationData() {
        disposable = OiApiManager.getApiServie().getClassData()
                .compose(new BaseCompose<List<ClassBean>>())
                .subscribe(new Consumer<List<ClassBean>>() {
                    @Override
                    public void accept(List<ClassBean> classBeans) throws Exception {
                        iView.showView(classBeans);
                    }
                }, new ErrorConsumer(mContext));
    }
}

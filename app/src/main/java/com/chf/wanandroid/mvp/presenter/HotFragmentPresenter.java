package com.chf.wanandroid.mvp.presenter;

import android.content.Context;
import android.view.View;

import com.chf.wanandroid.base.BasePresenter;
import com.chf.wanandroid.http.BaseCompose;
import com.chf.wanandroid.http.ErrorConsumer;
import com.chf.wanandroid.http.OiApiManager;
import com.chf.wanandroid.mvp.model.bean.HotKeyWordBean;
import com.chf.wanandroid.mvp.model.bean.HotWebBean;
import com.chf.wanandroid.mvp.view.HotFragmentView;
import com.chf.wanandroid.ui.activity.BaseWebViewActivity;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * @author 17919
 * @date 2018/3/20
 */

public class HotFragmentPresenter extends BasePresenter<HotFragmentView> {
    public HotFragmentPresenter(Context context, HotFragmentView iView) {
        super(context, iView);
    }

    /**
     * 获取热门单词
     */
    public void getHotKeyData() {
        disposable = OiApiManager.getApiServie().getHotKeyData()
                .compose(new BaseCompose<List<HotKeyWordBean>>())
                .subscribe(new Consumer<List<HotKeyWordBean>>() {
                    @Override
                    public void accept(List<HotKeyWordBean> keyWordBeans) throws Exception {
                        iView.showHotKeyView(keyWordBeans);

                    }
                }, new ErrorConsumer(mContext) {
                    @Override
                    public void onError(Throwable throwable) throws Exception {
                        super.onError(throwable);
                        iView.showErrorView();
                    }
                });
    }

    /**
     * 获取热门网址
     */
    public void getHotWebData() {
        iView.showProgress();
        disposable = OiApiManager.getApiServie().getHotWebData()
                .compose(new BaseCompose<List<HotWebBean>>())
                .subscribe(new Consumer<List<HotWebBean>>() {
                    @Override
                    public void accept(List<HotWebBean> webBeans) throws Exception {
                        iView.hideProgress();
                        iView.showHotWebView(webBeans);
                    }
                }, new ErrorConsumer(mContext) {
                    @Override
                    public void onError(Throwable throwable) throws Exception {
                        super.onError(throwable);
                        iView.showErrorView();
                    }
                });
    }

    public void onItemClick(View view, int position, List<HotWebBean> webBeans) {
        BaseWebViewActivity.actionStart(mContext,webBeans.get(position).getLink());
    }
}

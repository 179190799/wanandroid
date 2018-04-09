package com.chf.wanandroid.mvp.view;

import com.chf.wanandroid.base.BaseView;
import com.chf.wanandroid.mvp.model.bean.MyCollectionBean;

/**
 * @author 17919
 * @date 2018/4/8
 */

public interface MyCollectionView extends BaseView {

    void showView(MyCollectionBean beans);

    void unCollectSuccess();

    void showErrorView(String errorMsg);

}

package com.chf.wanandroid.mvp.model;

import com.chf.wanandroid.mvp.model.bean.ArticleBean;
import com.chf.wanandroid.mvp.model.bean.BannerBean;

import java.util.List;

/**
 * @author 17919
 * @date 2018/3/30
 */

public class HomeModel {

    public List<BannerBean> bannerBeans;
    public ArticleBean articleBean;

    @Override
    public String toString() {
        return "HomeModel{" +
                "bannerBeans=" + bannerBeans +
                ", articleBean=" + articleBean +
                '}';
    }
}

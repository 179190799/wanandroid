package com.chf.wanandroid.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chf.wanandroid.R;
import com.chf.wanandroid.mvp.model.bean.ArticleBean;
import com.chf.wanandroid.ui.utils.StringUtils;

import java.util.List;

/**
 * @author 17919
 * @date 2018/4/9
 */

public class ClassDetailAdapter extends BaseQuickAdapter<ArticleBean.DatasBean, BaseViewHolder> {

    public ClassDetailAdapter(int layoutResId, @Nullable List<ArticleBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean.DatasBean item) {
        helper.addOnClickListener(R.id.home_collection_iv);
        helper.setText(R.id.home_title_tv, item.getTitle());
        helper.setText(R.id.home_author_tv, item.getAuthor());
        helper.setText(R.id.home_nice_time_tv, item.getNiceDate());
        helper.setText(R.id.home_chapter_tv, item.getChapterName());

        if (!StringUtils.isLogin(mContext)) {
            helper.setImageResource(R.id.home_collection_iv, R.drawable.icon_collection_gray);
            return;
        } 
        if (item.isCollect()) {
//                是您的收藏
            helper.setImageResource(R.id.home_collection_iv, R.drawable.icon_collection);
        } else {
            helper.setImageResource(R.id.home_collection_iv, R.drawable.icon_collection_gray);
        }
        
    }
}

package com.chf.wanandroid.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chf.wanandroid.R;
import com.chf.wanandroid.mvp.model.bean.MyCollectionBean;

import java.util.List;

/**
 * @author 17919
 * @date 2018/4/9
 */

public class MyCollectionAdapter extends BaseQuickAdapter<MyCollectionBean.DatasBean, BaseViewHolder> {

    public MyCollectionAdapter(int layoutResId, @Nullable List<MyCollectionBean.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCollectionBean.DatasBean item) {
        helper.setText(R.id.home_title_tv, item.getTitle());
        helper.setText(R.id.home_author_tv, item.getAuthor());
        helper.setText(R.id.home_nice_time_tv, item.getNiceDate());
        helper.setText(R.id.home_chapter_tv, item.getChapterName());
        helper.setImageResource(R.id.home_collection_iv, R.drawable.icon_collection);
        helper.addOnClickListener(R.id.home_collection_iv);
    }
}

package com.chf.wanandroid.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chf.wanandroid.R;
import com.chf.wanandroid.mvp.model.bean.HotWebBean;

import java.util.List;

/**
 *
 * @author 17919
 * @date 2018/4/1
 */

public class HotWebAdapter extends BaseQuickAdapter<HotWebBean,BaseViewHolder> {

    public HotWebAdapter(int layoutResId, @Nullable List<HotWebBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotWebBean item) {
        helper.setText(R.id.item_hot_label_tv, item.getName());
    }


}

package com.chf.wanandroid.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chf.wanandroid.R;
import com.chf.wanandroid.mvp.model.bean.HotKeyWordBean;

import java.util.List;

/**
 *
 * @author 17919
 * @date 2018/4/1
 */

public class HotKeyAdapter extends BaseQuickAdapter<HotKeyWordBean,BaseViewHolder> {

    public HotKeyAdapter(int layoutResId, @Nullable List<HotKeyWordBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotKeyWordBean item) {
        helper.setText(R.id.item_hot_label_tv, item.getName());
    }


}

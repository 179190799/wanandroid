package com.chf.wanandroid.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chf.wanandroid.R;
import com.chf.wanandroid.mvp.model.bean.ClassBean;

import java.util.List;

/**
 * 知识体系二级标题适配器
 *
 * @author 17919
 * @date 2018/4/1
 */

public class ClassficationLabelAdapter extends BaseQuickAdapter<ClassBean.ChildrenBean,BaseViewHolder> {

    public ClassficationLabelAdapter(int layoutResId, @Nullable List<ClassBean.ChildrenBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ClassBean.ChildrenBean item) {
        helper.setText(R.id.item_label, item.getName());
        helper.addOnClickListener(R.id.item_label);
    }
}

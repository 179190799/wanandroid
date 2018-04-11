package com.chf.wanandroid.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chf.wanandroid.R;
import com.chf.wanandroid.mvp.model.bean.ClassBean;
import com.chf.wanandroid.ui.activity.ClassDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 知识体系适配器
 *
 * @author 17919
 * @date 2018/4/1
 */

public class ClassficationAdapter extends RecyclerView.Adapter {


    private Context mContext;
    private List<ClassBean> mData;

    public ClassficationAdapter(Context mContext, List<ClassBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public void setData(List<ClassBean> mData) {
        this.mData.addAll(mData);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClassViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_class, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        ClassViewHolder classViewHolder = (ClassViewHolder) holder;
        classViewHolder.mTitleTv.setText(mData.get(position).getName());
        RecyclerView mHotRv = classViewHolder.mHotRv;
        mHotRv.setLayoutManager(new GridLayoutManager(mContext, 4));
        ClassficationLabelAdapter classficationLabelAdapter = new ClassficationLabelAdapter(R.layout.item_class_label, mData.get(position).getChildren());
        mHotRv.setAdapter(classficationLabelAdapter);

        classficationLabelAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int mPosition) {
                ClassDetailActivity.actionStart(mContext,mData.get(position).getChildren().get(mPosition).getName(),mData.get(position).getChildren().get(mPosition).getId()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ClassViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_title_tv)
        TextView mTitleTv;
        @BindView(R.id.item_hot_rv)
        RecyclerView mHotRv;

        public ClassViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}

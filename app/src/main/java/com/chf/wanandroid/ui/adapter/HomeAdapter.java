package com.chf.wanandroid.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chf.wanandroid.R;
import com.chf.wanandroid.http.BaseResponse;
import com.chf.wanandroid.http.ErrorConsumer;
import com.chf.wanandroid.http.OiApiManager;
import com.chf.wanandroid.mvp.model.HomeModel;
import com.chf.wanandroid.mvp.model.bean.ArticleBean;
import com.chf.wanandroid.mvp.model.bean.BannerBean;
import com.chf.wanandroid.ui.glide.BannerUtils;
import com.chf.wanandroid.ui.utils.StringUtils;
import com.chf.wanandroid.ui.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 17919
 * @date 2018/3/30
 */

public class HomeAdapter extends RecyclerView.Adapter {


    private HomeModel mData;
    private Context mContext;

    private static final int banner_type = 1;
    private static final int content_type = 2;

    public HomeAdapter(HomeModel mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }


    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        if (viewType == banner_type) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_banner, parent, false);
            BannerViewHolder viewHolder = new BannerViewHolder(view);

            return viewHolder;
        } else if (viewType == content_type) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_content, parent, false);
            ContentViewHolder viewHolder = new ContentViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, (Integer) v.getTag());
                    }
                }
            });
            return viewHolder;
        } else {
            return null;
        }
    }

    public void setData(HomeModel data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void loadMoreData(HomeModel data) {
        mData.articleBean.getDatas().addAll(data.articleBean.getDatas());
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (getItemViewType(position) == banner_type) {
            final BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            List<String> titles = new ArrayList<>();
            List<String> images = new ArrayList<>();
            if (mData.bannerBeans != null) {

                List<BannerBean> bannerBeans = mData.bannerBeans;
                for (int i = 0; i < bannerBeans.size(); i++) {
                    titles.add(bannerBeans.get(i).getTitle());
                    images.add(bannerBeans.get(i).getImagePath());
                }
            }

            if (images.size() > 0) {
                BannerUtils.initBannerString(bannerViewHolder.mBanner, titles, images, false);
            }

            bannerViewHolder.mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int i) {
                    ToastUtil.showShort(mContext, i + "");
                }
            });


        } else if (getItemViewType(position) == content_type) {

            final ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
            contentViewHolder.itemView.setTag(position - 1);
            final ArticleBean.DatasBean datasBean = mData.articleBean.getDatas().get(position - 1);


            if (datasBean.isCollect()) {
//                是您的收藏
                if (StringUtils.isLogin(mContext)) {
                    contentViewHolder.mCollectionIv.setImageResource(R.drawable.icon_collection);
                }
            } else {
                contentViewHolder.mCollectionIv.setImageResource(R.drawable.icon_collection_gray);

            }

            contentViewHolder.mAuthorTv.setText(datasBean.getAuthor());
            contentViewHolder.mChapterTv.setText(datasBean.getChapterName());
            contentViewHolder.mNiceTimeTv.setText(datasBean.getNiceDate());
            contentViewHolder.mTitleTv.setText(datasBean.getTitle());

            contentViewHolder.mCollectionIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    检测是否登录
                    if (!StringUtils.isLogin(mContext)) {
                        ToastUtil.showShort(mContext, "请先登录");
                        return;
                    }
                    if (datasBean.isCollect()) {
//                        取消收藏
                        OiApiManager.getApiServie().unCollectionFromArticle(datasBean.getId())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<BaseResponse>() {
                                    @Override
                                    public void accept(BaseResponse baseResponse) throws Exception {
                                        if (baseResponse.errorCode == 0) {
                                            ToastUtil.showShort(mContext, "取消收藏成功");
                                            contentViewHolder.mCollectionIv.setImageResource(R.drawable.icon_collection_gray);
                                        } else {
                                            ToastUtil.showShort(mContext, baseResponse.errorMsg);
                                        }

                                    }
                                }, new ErrorConsumer(mContext));


                    } else {
//                        收藏
                        OiApiManager.getApiServie().collectionArticle(datasBean.getId())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<BaseResponse>() {
                                    @Override
                                    public void accept(BaseResponse baseResponse) throws Exception {
                                        if (baseResponse.errorCode == 0) {
                                            ToastUtil.showShort(mContext, "收藏成功");
                                            contentViewHolder.mCollectionIv.setImageResource(R.drawable.icon_collection);
                                        } else {
                                            ToastUtil.showShort(mContext, baseResponse.errorMsg);
                                        }
                                    }
                                }, new ErrorConsumer(mContext));

                    }
                }
            });


        }


    }

    @Override
    public int getItemCount() {
        if (mData.articleBean != null) {
            return mData.articleBean.getDatas().size() + 1;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return banner_type;
        }
        return content_type;
    }


    static class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_banner)
        Banner mBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_title_tv)
        TextView mTitleTv;
        @BindView(R.id.home_author_tv)
        TextView mAuthorTv;
        @BindView(R.id.home_chapter_tv)
        TextView mChapterTv;
        @BindView(R.id.home_nice_time_tv)
        TextView mNiceTimeTv;
        @BindView(R.id.home_collection_iv)
        ImageView mCollectionIv;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}

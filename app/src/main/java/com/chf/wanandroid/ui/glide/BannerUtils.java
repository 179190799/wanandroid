package com.chf.wanandroid.ui.glide;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

/**
 * Banner工具类
 *
 * @author Administrator
 * @date 2017/9/9
 */

public class BannerUtils {

    /**
     *
     * @param banner
     * @param imgs
     * @param isScroll
     * @param bannerStyle
     */
    public static void initBannerInteger(Banner banner, List<Integer> imgs, boolean isScroll, int bannerStyle) {
        banner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        banner.setImages(imgs);
        //是否允许手动滑动
        banner.setViewPagerIsScroll(isScroll);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        //设置banner样式
        banner.setBannerStyle(bannerStyle);

        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.CubeOut);

        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);


//        banner.setOnBannerListener(new OnBannerListener() {
//            @Override
//            public void OnBannerClick(int position) {
//                ToastUtil.showShort(mContext,"position:"+position);
//            }
//        });
    }

    /**
     * @param banner
     * @param titles      标题
     * @param imgs        图片
     * @param isScroll    是否允许手动滑动
     * @param bannerStyle banner样式
     */
    public static void initBannerString(Banner banner, List<String> titles, List<String> imgs, boolean isScroll) {

        banner.setImageLoader(new BannerImageLoader());
        //设置图片集合
        banner.setImages(imgs)
                .setBannerTitles(titles);
        //是否允许手动滑动
        banner.setViewPagerIsScroll(isScroll);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        //设置banner样式
//        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.CubeOut);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
    }
}

package com.chf.wanandroid.ui.glide;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Glide配置类,4.0以后新增
 * @author 17919
 * @date 2018/3/28
 */

@GlideModule
public class MyAppGlideModule extends AppGlideModule {

    /**
     * 禁用清单解析
     * 这样可以改善 Glide 的初始启动时间，并避免尝试解析元数据时的一些潜在问题。
     *
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}

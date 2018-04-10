package com.chf.wanandroid.ui.events;

/**
 * 用于刷新的EventBean
 *
 * @author 17919
 * @date 2018/4/9
 */

public class RefreshEvent {
    /**
     * 1：登录成功
     */
    public int type;

    public RefreshEvent(int type) {
        this.type = type;
    }
}

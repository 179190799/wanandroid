package com.chf.wanandroid.http;

import com.google.gson.Gson;

/**
 *
 * @author DuoNuo
 * @date 2017/9/15
 */

public class BaseResponse<T> {

    public int errorCode;
    public String errorMsg;
    public T data;

    public String toJson() {
        return new Gson().toJson(this);
    }
}

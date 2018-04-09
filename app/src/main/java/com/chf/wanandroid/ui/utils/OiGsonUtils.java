package com.chf.wanandroid.ui.utils;

import com.google.gson.Gson;

/**
 *
 * @author DuoNuo
 * @date 2017/12/25
 */

public class OiGsonUtils {

    private static class GsonHolder {
        private static Gson gson = new Gson();
    }

    private static Gson create(){
        return GsonHolder.gson;
    }

   public static String toJson(Object src){
        return create().toJson(src);
    }

}

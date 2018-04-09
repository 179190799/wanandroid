package com.chf.wanandroid.ui.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * SharedPreferences工具类
 *
 * @author Administrator
 * @date 2017/8/8
 */

public class SharedPreferencesUtil {
    //    保存在手机文件里面的文件名
    public static final String FILE_NAME = "share_data";


    /**
     * 创建一个解决 SharedPreferencesCompat.apply 方法的一个兼容类
     *
     * @author hanwen
     *         <p>
     *         2016-1-12
     */
    private static class SharedPreferencesCompat {

        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }


    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context 上下文
     * @param key     键
     * @param object  值
     */
    public static void put(Context context, String key, Object object) {
//        得到sharedPreference
        SharedPreferences spf = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
//        得到editor
        SharedPreferences.Editor editor = spf.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else if (object instanceof Set) {
            editor.putStringSet(key, (Set<String>) object);
        }
        SharedPreferencesCompat.apply(editor);
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对应的方法取值
     * @param context   上下文
     * @param key   键
     * @param object   默认值
     * @return
     */

    public static Object get(Context context,String key ,Object object) {

        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if(object instanceof String){
            return sp.getString(key, (String) object);
        }else if (object instanceof Integer) {
            return sp.getInt(key, (Integer) object);
        }else if (object instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) object);
        }else if (object instanceof Float) {
            return sp.getFloat(key, (Float) object);
        }else if (object instanceof Long) {
            return sp.getLong(key, (Long) object);
        } else if (object instanceof Set) {
            return sp.getStringSet(key, (Set<String>) object);
        }

        return null;

    }

    /**
     * 移除某个key值已经对应的值
     * @param context 上下文
     * @param key 键
     */
    public static void remove(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清楚所有数据
     * @param context 上下文
     */
    public static void clear(Context context){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context){
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getAll();
    }

}

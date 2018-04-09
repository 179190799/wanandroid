package com.chf.wanandroid.ui.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;

/**
 * 缓存清理工具
 *
 * @author Administrator
 * @date 2018/1/24
 */

public class CacheDataManager {

    public static String getTotalCacheSize(Context context) throws Exception {

        long cacheSize = getFolderSize(context.getCacheDir());

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            cacheSize += getFolderSize(context.getExternalCacheDir());

        }

        return getFormatSize(cacheSize);

    }

    public static void clearAllCache(Context context) {

        deleteDir(context.getCacheDir());

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            deleteDir(context.getExternalCacheDir());

        }

    }

    private static boolean deleteDir(File dir) {

        if (dir != null && dir.isDirectory()) {

            String[] children = dir.list();

            for (int i = 0; i < children.length; i++) {

                boolean success = deleteDir(new File(dir, children[i]));

                if (!success) {

                    return false;

                }

            }

        }

        return dir.delete();

    }

// 获取文件

// Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/

// 目录，一般放一些长时间保存的数据

// Context.getExternalCacheDir() -->

// SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据

    public static long getFolderSize(File file) throws Exception {

        long size = 0;

        try {

            File[] fileList = file.listFiles();

            for (int i = 0; i < fileList.length; i++) {

// 如果下面还有文件

                if (fileList[i].isDirectory()) {

                    size = size + getFolderSize(fileList[i]);

                } else {

                    size = size + fileList[i].length();

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return size;

    }

    /**
     * 格式化单位
     *
     * @param size
     */

    public static String getFormatSize(double size) {

        double kiloByte = size / 1024;

        if (kiloByte < 1) {

            return size + "Byte";

        }

        double megaByte = kiloByte / 1024;

        if (megaByte < 1) {

            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));

            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";

        }

        double gigaByte = megaByte / 1024;

        if (gigaByte < 1) {

            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));

            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";

        }

        double teraBytes = gigaByte / 1024;

        if (teraBytes < 1) {

            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));

            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";

        }

        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";

    }

/*
    在你要显示的TextView上显示清理前的缓存大小，在Activity的onCreate（）方法中直接设置就好了，其实就是调用了上面工具类的getTotalCacheSize（）方法，有异常，需要捕获。

            try {

        txtCacheSize.setText(CacheDataManager.getTotalCacheSize(this));

    } catch (Exception e) {

        e.printStackTrace();

    }

    再创建一个内部类，用于清理内存，实现了一个Runnable，清理完后发一个消息，这里可以灵活一点。

    class clearCache implements Runnable {

        @Override

        public void run() {

            try {

                CacheDataManager.clearAllCache(SettingsActivity.this);

                Thread.sleep(3000);

                if (CacheDataManager.getTotalCacheSize(SettingsActivity.this).startsWith("0")) {

                    handler.sendEmptyMessage(0);

                }

            } catch (Exception e) {

                return;

            }

        }

    }

    创建一个Handle接收消息，处理结果，其实用意是清理完了就弹一个吐司，清理完成，就是这样，也可以创建一个dialog，开始清理的时候显示，在下面方法关闭，然后再设置一遍TextView，就是下面这样。

    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {

                case 0:

                    Toast.makeText(SettingActivity.this,"清理完成",Toast.LENGTH_SHORT).show();

                    try {

                        txtCacheSize.setText(CacheDataManager.getTotalCacheSize(SettingsActivity.this));

                    } catch (Exception e) {

                        e.printStackTrace();

                    }

            }

        };

    };
    最后就是在设置监听的按钮中调用就好了。
    newThread(newclearCache()).start();

    当SD卡存在或者SD卡不可被移除的时候，就调用getExternalCacheDir()方法来获取缓存路径，
    否则就调用getCacheDir()方法来获取缓存路径。
    */


}

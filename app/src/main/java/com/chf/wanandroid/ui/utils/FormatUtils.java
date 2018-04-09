package com.chf.wanandroid.ui.utils;

import java.text.DecimalFormat;

/**
 * 格式化工具类
 *
 * @author Created by hf  2018-03-08 15:01
 * @version 1.0
 * @des
 * @版本 $Rev: 4513 $
 * @change $Author: wwei $  $Date: 2018-03-14 20:50:30 +0800 (周三, 14 三月 2018) $
 * @des ${TODO}
 */
public class FormatUtils {

    public static double formatToDouble(double num) {
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(num));
    }

    public static String formatToString(double num) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(num);
    }
}

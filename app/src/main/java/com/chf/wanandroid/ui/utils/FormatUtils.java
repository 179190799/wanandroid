package com.chf.wanandroid.ui.utils;

import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.util.Log;
import android.widget.EditText;

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

    private FormatUtils() {
    }

    public static FormatUtils getInstance() {
        return new FormatUtils();
    }

    public static double formatToDouble(double num) {
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(num));
    }

    public static String formatToString(double num) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(num);
    }

    public static double formatToDouble3(double num) {
        DecimalFormat df = new DecimalFormat("0.000");
        return Double.parseDouble(df.format(num));
    }

    public static String formatToString3(double num) {
        DecimalFormat df = new DecimalFormat("0.000");
        return df.format(num);
    }

    public void setDecimalsFilter(EditText editText) {
        editText.setFilters(new InputFilter[]{lengthFilter});
    }

    public void setIntegerFilter(EditText editText) {
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    /**
     * 输入框小数的位数
     */
    private static final int DECIMAL_DIGITS = 3;

    private InputFilter lengthFilter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            // source:当前输入的字符
            // start:输入字符的开始位置
            // end:输入字符的结束位置
            // dest：当前已显示的内容
            // dstart:当前光标开始位置
            // dent:当前光标结束位置
            Log.i("", "source=" + source + ",start=" + start + ",end=" + end
                    + ",dest=" + dest.toString() + ",dstart=" + dstart
                    + ",dend=" + dend);
            if (dest.length() == 0 && source.equals(".")) {
                return "0.";
            }
            String dValue = dest.toString();
            String[] splitArray = dValue.split("\\.");
            if (splitArray.length > 1) {
                String dotValue = splitArray[1];
                if (dotValue.length() == DECIMAL_DIGITS) {
                    return "";
                }
            }
            return null;
        }

    };

    static String[] units = {"","十","百","千","万","十万","百万","千万","亿","十亿","百亿","千亿","万亿" };
    static char[] numArray = {'零','一','二','三','四','五','六','七','八','九'};

    public static String formatInteger(int num) {
        char[] val = String.valueOf(num).toCharArray();
        int len = val.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String m = val[i] + "";
            int n = Integer.valueOf(m);
            boolean isZero = n == 0;
            String unit = units[(len - 1) - i];
            if (isZero) {
                if ('0' == val[i - 1]) {
                    continue;
                } else {
                    sb.append(numArray[n]);
                }
            } else {
                sb.append(numArray[n]);
                sb.append(unit);
            }
        }


        return sb.toString();
    }
}

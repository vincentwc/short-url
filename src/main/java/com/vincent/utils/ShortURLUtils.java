package com.cecloud.cdp.shorturl.utils;

import cn.hutool.core.text.CharSequenceUtil;

/**
 * @author wang_cheng
 * @date 2022/08/24 11:15
 * @desc 短链接转换
 **/
public class ShortURLUtils {

    private static final String BASE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 将十进制的数字转换成62进制的
     *
     * @param num    十进制数字
     * @param length 字符串长度
     * @return 62进制的字符串，长度不足左边补0
     */
    public static String toBase62(long num, int length) {
        StringBuilder result = new StringBuilder();
        do {
            int scale = 62;
            int i = (int) (num % scale);
            result.append(BASE.charAt(i));
            num /= scale;
        } while (num > 0);
        String str62 = result.reverse().toString();
        return CharSequenceUtil.padPre(str62, length, ParamConstant.ZERO_CHAR);
    }

    /**
     * 将62进制的字符串转换为10进制数字
     * @param str
     * @return
     */
    public static long toBase10(String str) {
        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            result = result * 62 + BASE.indexOf(str.charAt(i));
        }
        return result;
    }

}

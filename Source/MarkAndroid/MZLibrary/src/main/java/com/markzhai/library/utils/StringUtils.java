package com.markzhai.library.utils;

import java.nio.charset.Charset;

/**
 * Created by marktlzhai on 2015/1/7.
 */
public class StringUtils {

    private static final Charset GBK_CHARSET = Charset.forName("GBK");
    private static final Charset GB2312_CHARSET = Charset.forName("GB2312");
    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private static final Charset ISO_8859_1_CHARSET = Charset.forName("ISO-8859-1");

    /**
     * 判断是否为null
     *
     * @param cs
     * @return
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 检查手机号
     */
    public static boolean checkPhone(String mobile) {
        if (isEmpty(mobile)) {
            return false;
        }

        if (!NumberUtils.isNumber(mobile)) {
            return false;
        }

        int length = mobile.length();
        if (length != 11) {
            return false;
        }
        return true;
    }

    /**
     * 检查验证码
     */
    public static boolean checkVerifycode(String verifyCode) {
        if (isEmpty(verifyCode)) {
            return false;
        }

        if (!NumberUtils.isNumber(verifyCode)) {
            return false;
        }

        if (verifyCode.length() != 4) {
            return false;
        }
        return true;
    }

    public static String GBK2UTF8(String gbkStr) {
        return new String(gbkStr.getBytes(GBK_CHARSET), UTF8_CHARSET);
    }

    public static String GB23122UTF8(String gb2312Str) {
        return new String(gb2312Str.getBytes(GB2312_CHARSET), UTF8_CHARSET);
    }

    public static String ISO885912UTF8(String gb2312Str) {
        return new String(gb2312Str.getBytes(ISO_8859_1_CHARSET), UTF8_CHARSET);
    }
}

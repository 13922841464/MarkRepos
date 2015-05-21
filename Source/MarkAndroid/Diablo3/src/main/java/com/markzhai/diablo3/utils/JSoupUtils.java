package com.markzhai.diablo3.utils;

import org.jsoup.select.Elements;

/**
 * Created by marktlzhai on 2015/5/20.
 */
public class JSoupUtils {
    public static boolean isNotEmpty(Elements e) {
        if (e == null || e.isEmpty()) {
            return false;
        }
        return true;
    }
}

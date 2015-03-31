package com.markzhai.library.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间相关工具类
 */
public class TimeUtils {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");

    private TimeUtils() {
        throw new AssertionError();
    }

    public static String getMM_SS(int milliSecond) {
        double secondTime = (double) milliSecond / 1000d;
        int minute = (int) secondTime / 60;
        int second = (int) (secondTime - minute * 60);
        if (String.valueOf(second).length() < 2) {
            return String.valueOf(minute) + ":0" + String.valueOf(second);
        } else {
            return String.valueOf(minute) + ":" + String.valueOf(second);
        }
    }

    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }
}

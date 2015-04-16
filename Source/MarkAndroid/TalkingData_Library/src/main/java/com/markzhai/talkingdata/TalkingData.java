package com.markzhai.talkingdata;

import android.content.Context;

import com.tendcloud.tenddata.TCAgent;

import java.util.Map;

/**
 * Created by marktlzhai on 2015/4/16.
 */
public class TalkingData {

    public static void init(Context context, String appid, String channelid) {
        TCAgent.init(context, appid, channelid);
        TCAgent.setReportUncaughtExceptions(true);
    }

    public static void addGlobalValue(String key, Object value) {
        TCAgent.setGlobalKV(key, value);
    }

    public static void removeGlobalValue(String key, Object value) {
        TCAgent.removeGlobalKV(key);
    }

    public static void onEvent(Context context, String eventID) {
        TCAgent.onEvent(context, eventID);
    }

    public static void onEvent(Context context, String eventID, String eventLabel) {
        TCAgent.onEvent(context, eventID, eventLabel);
    }

    public static void onEvent(Context context, String eventID, String eventLabel, Map<String, String> data) {
        TCAgent.onEvent(context, eventID, eventLabel, data);
    }

    public static void onError(Context context, Throwable t) {
        TCAgent.onError(context, t);
    }
}

package com.markzhai.library.utils;

import android.app.Activity;
import android.content.Context;

import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.onlineconfig.UmengOnlineConfigureListener;
import com.umeng.fb.FeedbackAgent;
import com.umeng.update.UmengUpdateAgent;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by marktlzhai on 2015/4/8.
 */
public class UMengUtils {

    private static final String TAG = "UMengAnalytic";

    private static Context context;

    public static void init(Context context, String appkey, String appchannel) {
        UMengUtils.context = context;
        AnalyticsConfig.setAppkey(appkey);
        AnalyticsConfig.setChannel(appchannel);

        UmengUpdateAgent.setAppkey(appkey);
        UmengUpdateAgent.setChannel(appchannel);

        MobclickAgent.setCatchUncaughtExceptions(true);
    }

    public static void loadOnlineConf(Context context) {
        MobclickAgent.updateOnlineConfig(context);
        MobclickAgent.setOnlineConfigureListener(new UmengOnlineConfigureListener() {
            @Override
            public void onDataReceived(JSONObject jsonObject) {
                if (jsonObject != null && jsonObject.length() > 0) {
                    for (Iterator<String> iterator = jsonObject.keys(); iterator.hasNext(); ) {
                        String key = iterator.next().toString();
                        SPUtils.putString(key, jsonObject.optString(key));
                    }
                }
            }
        });
    }

    public static String getConf(String confName) {
        return SPUtils.getString(confName, "");
    }

    public static void onEvent(String eventID) {
        MobclickAgent.onEvent(context, eventID);
        NLog.d(TAG, "event:" + eventID);
    }

    public static void onEvent(String eventID, String data) {
        MobclickAgent.onEvent(context, eventID, data);
        NLog.d(TAG, "event:" + eventID + "|" + data);
    }

    public static void onEvent(String eventID, HashMap<String, String> data) {
        MobclickAgent.onEvent(context, eventID, data);
        NLog.d(TAG, "event:" + eventID + "|" + data);
    }

    public static void onEventValue(String eventID, int duration) {
        MobclickAgent.onEventValue(context, eventID, null, duration);
        NLog.d(TAG, "event value:" + eventID + "|" + duration);
    }

    public static void onEventValue(String eventID, HashMap<String, String> data, int duration) {
        MobclickAgent.onEventValue(context, eventID, data, duration);
        NLog.d(TAG, "event value:" + eventID + "|" + data + "|" + duration);
    }

    public static void reportError(String error) {
        MobclickAgent.reportError(context, error);
        NLog.d(TAG, "report error:" + error);
    }

    public static void reportError(Throwable throwable) {
        MobclickAgent.reportError(context, throwable);
        NLog.d(TAG, "report error:" + throwable.getMessage());
    }

    public static void feedback(Activity activity) {
        FeedbackAgent feedbackAgent = new FeedbackAgent(activity);
        feedbackAgent.startFeedbackActivity();
    }

    public static void checkUpdate() {
        UmengUpdateAgent.update(context);
    }
}

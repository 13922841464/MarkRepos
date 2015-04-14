package com.markzhai.library.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;

import com.markzhai.library.framework.BaseApplication;

import java.util.List;

/**
 * 程序相关工具类 Created by marktlzhai on 2014/12/24.
 */
public class AppUtils {

    public static void exit() {
        NLog.d("Application will exit.");
        System.exit(0);
        Process.killProcess(Process.myPid());
    }

    /**
     * 判断程序是否在后台运行
     *
     * @param context
     * @return
     */
    public static boolean isApplicationInBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName topActivity = taskList.get(0).topActivity;
            if (topActivity != null && !topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取ApplicationInfo
     */
    public static final ApplicationInfo getApplicationInfo(Application app) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = app.getPackageManager().getApplicationInfo(app.getPackageName(), PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return appInfo;
    }

    /**
     * 获取MateData
     */
    public static final Bundle getMetaData(Application app) {
        return getApplicationInfo(app).metaData;
    }

    /**
     * 获取Version Code
     *
     * @return
     */
    public static int getVersionCode() {
        PackageManager pm = BaseApplication.getApplication().getPackageManager();
        int appVersion = 0;
        try {
            appVersion = pm.getPackageInfo(BaseApplication.getApplication().getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return appVersion;
    }

    /**
     * 获取Version Name
     * @return
     */
    public static String getVersionName() {
        PackageManager pm = BaseApplication.getApplication().getPackageManager();
        String appVersion = "";
        try {
            appVersion = pm.getPackageInfo(BaseApplication.getApplication().getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return appVersion;
    }

    public static String getCurrentDeviceFullModel() {

        String deviceFullModel = "";
        if (Build.MANUFACTURER == null || Build.MODEL == null)
            return deviceFullModel;
        deviceFullModel = Build.MANUFACTURER + "(" + Build.MODEL + ")";
        return deviceFullModel;
    }

    public static void openMarket(String packageName) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.getApplication().startActivity(intent);
    }
}

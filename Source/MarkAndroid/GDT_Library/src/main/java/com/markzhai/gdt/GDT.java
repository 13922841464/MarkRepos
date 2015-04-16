package com.markzhai.gdt;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.qq.e.ads.AdListener;
import com.qq.e.ads.AdRequest;
import com.qq.e.ads.AdSize;
import com.qq.e.ads.AdView;
import com.qq.e.ads.InterstitialAd;
import com.qq.e.appwall.GdtAppwall;
import com.qq.e.gdtnativead.GDTNativeAd;
import com.qq.e.gdtnativead.GDTNativeAdDataRef;
import com.qq.e.gridappwall.GridAppWall;
import com.qq.e.gridappwall.GridAppWallListener;
import com.qq.e.splash.SplashAd;
import com.qq.e.splash.SplashAdListener;

import java.util.List;

/**
 * Created by marktlzhai on 2015/4/16.
 */
public class GDT {

    public static interface NativeADLoadCallback {
        void loadloadCallbackSuccess(List<GDTNativeAdDataRef> gdtNativeAdDataRefs);

        void loadloadCallbackError();
    }

    /**
     * 显示一个banner广告
     */
    public static void showBannerAD(Activity activity, ViewGroup adContainer, String appid, String posid, AdListener listener) {
        AdView bannerAD = new AdView(activity, AdSize.BANNER, appid, posid);
        if (listener != null) {
            bannerAD.setAdListener(listener);
        }
        adContainer.removeAllViews();
        adContainer.addView(bannerAD);
        bannerAD.fetchAd(new AdRequest());
    }

    /**
     * 在listview中的Header上添加一个广告
     */
    public static void showBannerADOnListHeader(Activity activity, ListView list, String appid, String posid, AdListener listener) {
        AdView bannerAD = new AdView(activity, AdSize.BANNER, appid, posid);
        if (listener != null) {
            bannerAD.setAdListener(listener);
        }
        list.addHeaderView(bannerAD);
        bannerAD.fetchAd(new AdRequest());
    }

    /**
     * 加载插屏广告
     */
    public static InterstitialAd loadInterstitialAD(Activity activity, String appid, String posid) {
        InterstitialAd iad = new InterstitialAd(activity, appid, posid);
        iad.loadAd();
        return iad;
    }

    /**
     * 显示插屏广告
     */
    public static void showInterstitialAD(InterstitialAd iad) {
        iad.showAsPopupWindown();
    }

    /**
     * 关闭插屏广告
     */
    public static void closeInterstitialAD(InterstitialAd iad) {
        iad.closePopupWindow();
    }

    /**
     * 显示应用墙
     */
    public static void showAppWall(Activity activity, String appid, String posid) {
        GdtAppwall wall = new GdtAppwall(activity, appid, posid, false);
        wall.doShowAppWall();
    }

    /**
     * 显示Grid应用墙 actionview为触发动作的view
     */
    public static void showGridAppWall(Activity activity, String appid, String posid, View actionView, GridAppWallListener listener) {
        GridAppWall gw = new GridAppWall(appid, posid, activity, listener);
        gw.showRelativeTo(actionView);
    }

    /**
     * 显示Splash广告
     */
    public static void showSplashAD(Activity activity, ViewGroup adContainer, String appid, String posid, SplashAdListener listener) {
        new SplashAd(activity, adContainer, appid, posid, listener);
    }

    /**
     * 加载native广告
     */
    public static void loadNativeAD(Activity activity, String appid, String posid, final NativeADLoadCallback loadCallback, int count) {
        GDTNativeAd nativeAd = new GDTNativeAd(activity, appid, posid, new GDTNativeAd.GDTNativeAdListener() {
            @Override
            public void onGDTNativeAdLoaded(List<GDTNativeAdDataRef> gdtNativeAdDataRefs) {
                if (loadCallback != null) {
                    loadCallback.loadloadCallbackSuccess(gdtNativeAdDataRefs);
                }
            }

            @Override
            public void onGDTNativeAdFail(int i) {
                if (loadCallback != null) {
                    loadCallback.loadloadCallbackError();
                }
            }
        });
        nativeAd.loadAd(count);
    }
}

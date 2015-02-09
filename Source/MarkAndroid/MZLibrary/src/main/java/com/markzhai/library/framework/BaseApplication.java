package com.markzhai.library.framework;

import android.app.Application;
import android.content.pm.PackageManager;
import android.util.DisplayMetrics;

import com.markzhai.library.utils.ImageUtils;
import com.markzhai.library.utils.NLog;
import com.markzhai.library.utils.SPUtils;
import com.markzhai.library.utils.SigntureUtil;
import com.tencent.tauth.Tencent;

/**
 * Created by marktlzhai on 2015/1/24.
 */
public class BaseApplication extends Application {
    /**
     * 屏幕宽度（像素）
     */
    public static int SCREEN_WIDTH;

    /**
     * 屏幕高度（像素）
     */
    public static int SCREEN_HEIGHT;

    /**
     * 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
     */
    public static float DENSITY;

    /**
     * 屏幕密度（每寸像素：120/160/240/320）
     */
    public static int DENSITY_DPI;

    protected static Application instance;

    public static Tencent tencent;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // 初始化屏幕参数
        DisplayMetrics dm = getResources().getDisplayMetrics();
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        DENSITY = dm.density;
        DENSITY_DPI = dm.densityDpi;
        NLog.d(BaseApplication.class, "Screen Width : " + SCREEN_WIDTH + ", Screen Height : " + SCREEN_HEIGHT);
        NLog.d(BaseApplication.class, "Density : " + DENSITY + ", Density DPI : " + DENSITY_DPI);

        // 初始化图片加载组件
        ImageUtils.init(this);

        // 初始化SP
        SPUtils.init(this);

        NLog.d("============== Signture Information===============");
        NLog.d(SigntureUtil.getSignture(instance));
        NLog.d("============== Signture Information===============");
    }

    /**
     * 初始化 Tencent
     */
    public void initTencent(String appid) {
        tencent = Tencent.createInstance(appid, this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        // 销毁图片加载组件
        ImageUtils.destroy();
    }

    public static Application getApplication() {
        return instance;
    }
}

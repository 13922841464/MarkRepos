package com.markzhai.library.framework;

import android.app.Application;
import android.util.DisplayMetrics;

import com.markzhai.library.utils.ImageUtils;
import com.markzhai.library.utils.NLog;
import com.markzhai.library.utils.SPUtils;
import com.markzhai.library.utils.SigntureUtil;
import com.markzhai.library.utils.UMengUtils;
import com.umeng.analytics.AnalyticsConfig;

/**
 * Created by marktlzhai on 2015/1/24.
 */
public abstract class BaseApplication extends Application {

    protected static final String CHANNEL_DEVELOPER = "DEVELOPER";
    protected static final String CHANNEL_UMENG = "UMENG";
    protected static final String CHANNEL_MIKANDI = "MIKANDI";
    protected static final String CHANNEL_1MOBILE = "1MOBILE";
    protected static final String CHANNEL_GOOGLE = "GOOGLE_PLAY";
    protected static final String CHANNEL_TENCENT = "TENCENT_STORE";
    protected static final String CHANNEL_BAIDU = "BAIDU_STORE";

    public abstract String getUMengAppKey();

    public abstract String getUMengChannel();

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

    public static boolean ormSupport = false;

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

        // 初始化友盟组件
        if (getUMengAppKey() != null && getUMengChannel() != null) {
            UMengUtils.init(this, getUMengAppKey(), getUMengChannel());
        }

        NLog.d("============== Signture Information===============");
        NLog.d(SigntureUtil.getSignture(instance));
        NLog.d("============== Signture Information===============");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        // 销毁图片加载组件
        ImageUtils.destroy();

        ormSupport = false;
    }

    public static Application getApplication() {
        return instance;
    }
}

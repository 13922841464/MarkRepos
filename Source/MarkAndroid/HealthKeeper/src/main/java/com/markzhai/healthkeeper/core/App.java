package com.markzhai.healthkeeper.core;

import com.markzhai.library.framework.BaseActivity;
import com.markzhai.library.framework.BaseApplication;
import com.markzhai.talkingdata.TalkingData;

/**
 * Created by marktlzhai on 2015/4/14.
 */
public class App extends BaseApplication {

    private static final String TALKING_DATA_APP_ID = "BD0E6A065158433ECFC1D26208ED2F66";

    public static final String GDT_APPID = "1104540344";
    public static final String GDT_POSID_BANNER = "9020403268162236";
    public static final String GDT_POSID_APP_WALL = "6080203278566247";
    public static final String GDT_POSID_INTERSTITIAL = "1030700258869268";
    public static final String GDT_POSID_SPLASH = "4050202258860219";

    @Override
    public void onCreate() {
        super.onCreate();
        TalkingData.init(this, TALKING_DATA_APP_ID, CHANNEL_DEVELOPER);
    }
}

package com.markzhai.adultvideo.core;

import com.markzhai.library.framework.BaseApplication;

/**
 * Created by marktlzhai on 2015/3/23.
 */
public class App extends BaseApplication {

    private static final String CHANNEL_UMENG = "UMENG";
    private static final String CHANNEL_MIKANDI = "MIKANDI";
    private static final String CHANNEL_1MOBILE = "1MOBILE";

    public static final String EVENT_BUFF_COST_TIME = "BUFF_COST_TIME";
    public static final String EVENT_CLICK_NORMAL_AD = "CLICK_NORMAL_AD";
    public static final String EVENT_CLICK_PAGE_AD = "CLICK_PAGE_AD";
    public static final String EVENT_PLAY_TOO_SLOW = "PLAY_TOO_SLOW";

    @Override
    public String getUMengAppKey() {
        return "55248cb5fd98c5a773000c21";
    }

    @Override
    public String getUMengChannel() {
        return CHANNEL_UMENG;
    }
}

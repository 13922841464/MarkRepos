package com.markzhai.healthkeeper.core;

import com.markzhai.library.framework.BaseApplication;

/**
 * Created by marktlzhai on 2015/4/14.
 */
public class App extends BaseApplication {
    @Override
    public String getUMengAppKey() {
        return "552c9282fd98c53d8b000081";
    }

    @Override
    public String getUMengChannel() {
        return CHANNEL_DEVELOPER;
    }
}

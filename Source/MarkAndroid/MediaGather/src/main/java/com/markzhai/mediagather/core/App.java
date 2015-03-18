package com.markzhai.mediagather.core;

import com.markzhai.library.framework.BaseApplication;
import com.markzhai.mediagather.core.model.sys.Configuration;

/**
 * Created by marktlzhai on 2015/3/18.
 */
public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initORM();

        Configuration configuration = new Configuration();
        configuration.confName = "testName";
        configuration.confValue = "testValut";
        configuration.save();
    }
}

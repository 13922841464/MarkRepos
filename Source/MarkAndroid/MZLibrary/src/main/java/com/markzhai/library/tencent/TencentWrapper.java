package com.markzhai.library.tencent;

import com.markzhai.library.framework.BaseApplication;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.tencent.listener.LoginListener;
import com.markzhai.library.tencent.module.TencentSession;
import com.markzhai.library.utils.SPUtils;
import com.markzhai.library.utils.StringUtils;

/**
 * Created by marktlzhai on 2015/2/9.
 */
public class TencentWrapper {

    public static class TencentSP {
        public static final String STRING_PAY_TOKEN = "PAY_TOKEN";
        public static final String STRING_PF = "PF";
        public static final String LONG_EXPIRES = "EXPIRES";
        public static final String STRING_OPEN_ID = "OPEN_ID";
        public static final String STRING_PF_KEY = "PF_KEY";
        public static final String STRING_ACCESS_TOKEN = "ACCESS_TOKEN";
    }

    public static void login(BaseFragment fragment, LoginListener listener) {
        if (BaseApplication.tencent != null) {

            String openID = SPUtils.getString(TencentSP.STRING_OPEN_ID, "");
            String accessToken = SPUtils.getString(TencentSP.STRING_ACCESS_TOKEN, "");
            long expires = SPUtils.getLong(TencentSP.LONG_EXPIRES, 0);

            if (!StringUtils.isEmpty(openID) && !StringUtils.isEmpty(accessToken) && expires > 0) {
                BaseApplication.tencent.setOpenId(openID);
                BaseApplication.tencent.setAccessToken(accessToken, String.valueOf(expires));
            }

            BaseApplication.tencent.login(fragment, "all", listener);
        }
    }

    public static void logout() {
        if (BaseApplication.tencent != null) {
            BaseApplication.tencent.logout(BaseApplication.getApplication());
        }
    }

    public static void getUserInfo() {
        if (BaseApplication.tencent != null) {
            BaseApplication.tencent.logout(BaseApplication.getApplication());
        }
    }

    public static void saveLoginStatus(TencentSession session) {
        SPUtils.putString(TencentSP.STRING_PAY_TOKEN, session.payToken);
        SPUtils.putString(TencentSP.STRING_PF, session.pf);
        SPUtils.putString(TencentSP.STRING_OPEN_ID, session.openID);
        SPUtils.putString(TencentSP.STRING_PF_KEY, session.pfkey);
        SPUtils.putString(TencentSP.STRING_ACCESS_TOKEN, session.accessToken);
        SPUtils.putLong(TencentSP.LONG_EXPIRES, session.expires);
    }
}

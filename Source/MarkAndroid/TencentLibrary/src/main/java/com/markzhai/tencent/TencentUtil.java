package com.markzhai.tencent;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.markzhai.library.framework.BaseApplication;
import com.markzhai.library.utils.SPUtils;
import com.markzhai.library.utils.StringUtils;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * 腾讯相关工具类
 * Created by marktlzhai on 2015/4/24.
 */
public class TencentUtil {

    public static interface LoginCallback {
        void loginSuccess(QQModel result);

        void loginFailure(String errorMessage);
    }

    private static final String SP_QQ_OPENID = "QQ_OPENID";
    private static final String SP_QQ_ACCESS_TOKEN = "QQ_ACCESS_TOKEN";
    private static final String SP_QQ_EXPIRES_IN = "QQ_EXPIRES_IN";

    private static Tencent tencent;

    public static void init(String appid) {
        if (tencent == null) {
            tencent = Tencent.createInstance(appid, BaseApplication.getApplication());
        }
    }

    /**
     * 登录
     */
    public static void loginQQ(Fragment fragment, final LoginCallback callback) {
        String openID = SPUtils.getString(SP_QQ_OPENID, "");
        String accessToken = SPUtils.getString(SP_QQ_ACCESS_TOKEN, "");
        final String expiresIn = SPUtils.getString(SP_QQ_EXPIRES_IN, "");

        if (!StringUtils.isEmpty(openID)) {
            tencent.setOpenId(openID);
        }

        if (!StringUtils.isEmpty(accessToken) && !StringUtils.isEmpty(expiresIn)) {
            tencent.setAccessToken(accessToken, expiresIn);
        }

        tencent.login(fragment, "all", new IUiListener() {
            @Override
            public void onComplete(Object o) {
                JSONObject result = (JSONObject) o;
                try {
                    String token = result.getString(Constants.PARAM_ACCESS_TOKEN);
                    String expires = result.getString(Constants.PARAM_EXPIRES_IN);
                    String openId = result.getString(Constants.PARAM_OPEN_ID);
                    if (!StringUtils.isEmpty(token) && !StringUtils.isEmpty(expires) && !StringUtils.isEmpty(openId)) {
                        tencent.setAccessToken(token, expires);
                        tencent.setOpenId(openId);
                        if (callback != null) {
                            callback.loginSuccess(new QQModel(token, expiresIn, openId));
                        }
                    }
                } catch (Exception e) {
                    if (callback != null) {
                        callback.loginFailure("登录失败");
                    }
                }
            }

            @Override
            public void onError(UiError uiError) {
                if (callback != null) {
                    callback.loginFailure(uiError.errorDetail);
                }
            }

            @Override
            public void onCancel() {
                if (callback != null) {
                    callback.loginFailure("用户取消登录");
                }
            }
        });
    }
}

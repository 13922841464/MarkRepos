package com.markzhai.library.tencent.listener;

import com.markzhai.library.tencent.TencentWrapper;
import com.markzhai.library.tencent.module.TencentSession;
import com.markzhai.library.utils.NLog;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by marktlzhai on 2015/2/9.
 */
public abstract class LoginListener implements IUiListener {

    public abstract void onSuccess(TencentSession session);

    @Override
    public void onComplete(Object o) {
        try {
            JSONObject jsonObject = new JSONObject(o.toString());
            if (jsonObject != null) {
                String payToken = jsonObject.optString("pay_token");
                String pf = jsonObject.optString("pf");
                long expires = jsonObject.optLong("expires_in");
                String openID = jsonObject.optString("openid");
                String pfkey = jsonObject.optString("pfkey");
                String accessToken = jsonObject.optString("access_token");
                TencentSession session = new TencentSession(payToken, pf, expires, openID, pfkey, accessToken);

                TencentWrapper.saveLoginStatus(session);

                onSuccess(session);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            NLog.e(e.getMessage(), e);
            onError(new UiError(-9999, "解析失败", e.getMessage()));
        }
    }
}

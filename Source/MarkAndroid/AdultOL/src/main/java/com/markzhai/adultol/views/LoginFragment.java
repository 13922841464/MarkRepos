package com.markzhai.adultol.views;

import com.markzhai.adultol.R;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.tencent.TencentWrapper;
import com.markzhai.library.tencent.listener.LoginListener;
import com.markzhai.library.tencent.module.TencentSession;
import com.tencent.tauth.UiError;

/**
 * Created by marktlzhai on 2015/2/6.
 */
public class LoginFragment extends BaseFragment {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {
        TencentWrapper.login(this, new LoginListener() {
            @Override
            public void onSuccess(TencentSession session) {
                showToast("登陆成功\n=====================\n" + session.toString());
            }

            @Override
            public void onError(UiError uiError) {
                showToast("登陆失败" + uiError.errorDetail);
            }

            @Override
            public void onCancel() {
                showToast("登陆取消");
            }
        });
    }
}

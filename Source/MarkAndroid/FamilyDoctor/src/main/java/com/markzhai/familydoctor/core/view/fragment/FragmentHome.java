package com.markzhai.familydoctor.core.view.fragment;

import com.markzhai.familydoctor.R;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.widget.MZTopbar;
import com.markzhai.tencent.QQModel;
import com.markzhai.tencent.TencentUtil;

/**
 * Created by marktlzhai on 2015/4/24.
 */
public class FragmentHome extends BaseFragment {
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        TencentUtil.loginQQ(this, new TencentUtil.LoginCallback() {
            @Override
            public void loginSuccess(QQModel result) {
                showToast("login success\n" + result.accessToken + "\n" + result.expiresIn + "\n" + result.openID);
            }

            @Override
            public void loginFailure(String errorMessage) {
                showToast(errorMessage);
            }
        });
    }

    @Override
    public void initTopbar(MZTopbar topbar) {
        super.initTopbar(topbar);
    }
}

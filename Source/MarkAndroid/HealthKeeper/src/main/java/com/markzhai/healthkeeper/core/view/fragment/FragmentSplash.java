package com.markzhai.healthkeeper.core.view.fragment;

import android.widget.TextView;

import com.markzhai.healthkeeper.R;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.utils.AppUtils;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/4/14.
 */
public class FragmentSplash extends BaseFragment {

    @InjectView(R.id.splash_version)
    private TextView versionView;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void init() {
        versionView.setText(AppUtils.getVersionName());
    }
}

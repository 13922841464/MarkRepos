package com.markzhai.familydoctor.core.view.fragment;

import android.widget.TextView;

import com.markzhai.familydoctor.BuildConfig;
import com.markzhai.familydoctor.R;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.framework.page.FragmentType;
import com.markzhai.library.utils.AppUtils;
import com.markzhai.talkingdata.TalkingDataFragment;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/4/14.
 */
public class FragmentSplash extends TalkingDataFragment {

    @InjectView(R.id.splash_version)
    private TextView versionView;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void init() {
        versionView.setText(AppUtils.getVersionName());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentRequest homeRequest = new FragmentRequest(FragmentType.HOME, FragmentHome.class, false, false, null);
                startFragment(homeRequest);
            }
        }, BuildConfig.DEBUG ? 0L : 2000L);
    }

    @Override
    public void initData() {

    }
}

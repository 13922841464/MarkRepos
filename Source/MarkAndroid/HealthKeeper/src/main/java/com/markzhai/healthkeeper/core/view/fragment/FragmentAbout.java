package com.markzhai.healthkeeper.core.view.fragment;

import android.view.View;
import android.widget.TextView;

import com.markzhai.healthkeeper.R;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.utils.AppUtils;
import com.markzhai.library.widget.MZTopbar;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/4/21.
 */
public class FragmentAbout extends BaseFragment {

    @InjectView(R.id.splash_version)
    private TextView versionView;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_about;
    }

    @Override
    public void init() {
        versionView.setText(AppUtils.getVersionName());
    }

    @Override
    public void initTopbar(MZTopbar topbar) {
        super.initTopbar(topbar);
        topbar.setTitle(com.markzhai.library.R.string.about);
        topbar.setIcon(R.drawable.left_arrow, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBaseActivity().onBackPressed();
            }
        });
        topbar.setMenu(0, null);
    }
}

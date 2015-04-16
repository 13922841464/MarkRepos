package com.markzhai.healthkeeper.core.view.fragment;

import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.markzhai.gdt.GDT;
import com.markzhai.healthkeeper.R;
import com.markzhai.healthkeeper.core.App;
import com.markzhai.library.utils.NLog;
import com.markzhai.library.widget.MZTopbar;
import com.markzhai.talkingdata.TalkingDataFragment;
import com.qq.e.ads.AdListener;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/4/14.
 */
public class FragmentHome extends TalkingDataFragment {

    @InjectView(R.id.home_drawer)
    private DrawerLayout drawer;

    @InjectView(R.id.main_page_list)
    private ListView mainPageList;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        GDT.showBannerAD(getBaseActivity(), topbar, App.GDT_APPID, App.GDT_POSID_BANNER, new AdListener() {
            @Override
            public void onNoAd() {
                NLog.d(getFragmentTag(), "onNoAd");
            }

            @Override
            public void onAdReceiv() {
                NLog.d(getFragmentTag(), "onAdReceiv");
            }

            @Override
            public void onAdExposure() {
                NLog.d(getFragmentTag(), "onAdExposure");
            }

            @Override
            public void onBannerClosed() {
                NLog.d(getFragmentTag(), "onBannerClosed");
            }

            @Override
            public void onAdClicked() {
                NLog.d(getFragmentTag(), "onAdClicked");
            }
        });
    }

    @Override
    public void initTopbar(MZTopbar topbar) {
        super.initTopbar(topbar);
        topbar.setTitle(R.string.category_medical_center);
        topbar.setIcon(R.drawable.icon_medical_center_normal, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDrawer();
            }
        });
        topbar.setMenu(0, null);
    }

    private void toggleDrawer() {
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START);
        } else {
            drawer.openDrawer(Gravity.START);
        }
    }
}

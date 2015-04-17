package com.markzhai.healthkeeper.core.view.fragment;

import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;

import com.markzhai.healthkeeper.R;
import com.markzhai.library.widget.MZTopbar;
import com.markzhai.talkingdata.TalkingDataFragment;

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

    }

    @Override
    public void initTopbar(final MZTopbar topbar) {
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

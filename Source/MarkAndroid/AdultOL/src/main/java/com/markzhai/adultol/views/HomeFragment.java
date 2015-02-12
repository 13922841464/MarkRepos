package com.markzhai.adultol.views;

import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.markzhai.adultol.R;
import com.markzhai.adultol.views.widget.DrawerMenu;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.utils.UIUtils;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/2/5.
 */
public class HomeFragment extends BaseFragment {

    @InjectView(R.id.drawer_layout)
    private DrawerLayout drawerLayout;

    @InjectView(R.id.start_drawer)
    private RelativeLayout startDrawer;

    private DrawerMenu drawerMenu;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        drawerMenu = new DrawerMenu(getBaseActivity());
        startDrawer.addView(drawerMenu, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public boolean isDrawerOpened() {
        return drawerLayout.isDrawerOpen(Gravity.START);
    }

    public void openDrawer() {
        drawerLayout.openDrawer(Gravity.START);
    }

    public void closeDrawer() {
        drawerLayout.closeDrawer(Gravity.START);
    }

    public void toggleDrawer() {
        if (isDrawerOpened()) {
            closeDrawer();
        } else {
            openDrawer();
        }
    }
}

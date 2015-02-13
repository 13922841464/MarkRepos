package com.markzhai.adultol.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.markzhai.adultol.R;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.widget.viewpagerindicator.IconPagerAdapter;
import com.markzhai.widget.viewpagerindicator.TabPageIndicator;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/2/5.
 */
public class HomeFragment extends BaseFragment {

    @InjectView(R.id.home_pager_indicator)
    private TabPageIndicator pageIndicator;

    @InjectView(R.id.home_pager)
    private ViewPager homePager;
    private HomePagerAdapter homePagerAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        homePagerAdapter = new HomePagerAdapter(getBaseActivity().getSupportFragmentManager());
        homePager.setAdapter(homePagerAdapter);
        pageIndicator.setViewPager(homePager);
    }

    class HomePagerAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

        public HomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public int getIconResId(int index) {
            return 0;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}

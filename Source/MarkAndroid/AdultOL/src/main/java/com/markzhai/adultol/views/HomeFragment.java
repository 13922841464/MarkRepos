package com.markzhai.adultol.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.markzhai.adultol.R;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.widget.viewpagerindicator.TabPageIndicator;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/2/5.
 */
public class HomeFragment extends BaseFragment {

    @InjectView(R.id.home_pager_ind)
    private TabPageIndicator pagerIndicator;

    @InjectView(R.id.home_pager)
    private ViewPager homePager;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        homePager.setAdapter(new FragmentPagerAdapter(getBaseActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new SplashFragment();
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Title " + position;
            }
        });
        pagerIndicator.setViewPager(homePager);
    }
}

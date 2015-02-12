package com.markzhai.adultol;

import android.graphics.Typeface;

import com.markzhai.adultol.views.HomeFragment;
import com.markzhai.adultol.views.SplashFragment;
import com.markzhai.library.framework.BaseActivity;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.framework.page.FragmentType;

public class MainActivity extends BaseActivity {

    private static Typeface defaultFont = null;

    @Override
    protected FragmentRequest installHome() {
        return new FragmentRequest(FragmentType.HOME, SplashFragment.class, false, true, null);
    }

    @Override
    public Typeface installFont() {

        if (defaultFont == null) {
            defaultFont = Typeface.createFromAsset(getAssets(), "default.TTF");
        }

        return defaultFont;
    }

    @Override
    public void onBackPressed() {
        BaseFragment currentFramgnet = defaultFragmentManager.getCurrentFragment();
        if (currentFramgnet instanceof HomeFragment) {
            if (((HomeFragment) currentFramgnet).isDrawerOpened()) {
                ((HomeFragment) currentFramgnet).closeDrawer();
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }
}

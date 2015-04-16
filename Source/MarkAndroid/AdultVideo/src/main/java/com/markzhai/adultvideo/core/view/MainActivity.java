package com.markzhai.adultvideo.core.view;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.markzhai.adultvideo.R;
import com.markzhai.adultvideo.core.view.fragment.FragmentSplash;
import com.markzhai.library.framework.BaseActivity;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.framework.page.FragmentType;

public class MainActivity extends BaseActivity {

    private InterstitialAd interstitialAD;

    private AdRequest adRequest;

    private boolean isShowAD = false;

    @Override
    protected FragmentRequest installHome() {
        FragmentRequest request = new FragmentRequest(FragmentType.HOME, FragmentSplash.class, false, true, getIntent().getExtras());
        return request;
    }

    @Override
    protected void onResume() {
        super.onResume();

        adRequest = new AdRequest.Builder().build();

        interstitialAD = new InterstitialAd(this);
        interstitialAD.setAdUnitId(getString(R.string.ad_page));
        interstitialAD.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        if (interstitialAD.isLoaded()) {

            if (isShowAD) {
                super.onBackPressed();
            } else {
                interstitialAD.show();
                isShowAD = true;
            }
        } else {
            super.onBackPressed();
        }
    }
}

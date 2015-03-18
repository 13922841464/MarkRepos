package com.markzhai.mediagather.core.view.fragment;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.androidanimations.library.attention.RubberBandAnimator;
import com.daimajia.androidanimations.library.attention.StandUpAnimator;
import com.daimajia.androidanimations.library.fading_entrances.FadeInAnimator;
import com.daimajia.androidanimations.library.flippers.FlipInXAnimator;
import com.daimajia.androidanimations.library.sliders.SlideInUpAnimator;
import com.daimajia.androidanimations.library.specials.in.DropOutAnimator;
import com.daimajia.androidanimations.library.specials.in.LandingAnimator;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.framework.page.FragmentType;
import com.markzhai.mediagather.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/3/16.
 */
public class FragmentSplash extends BaseFragment {

    @InjectView(R.id.splash_logo)
    private ImageView logoView;

    @InjectView(R.id.splash_appname)
    private TextView appnameView;

    @InjectView(R.id.loading_staus)
    private TextView loadingStatusView;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void init() {
        initViews();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initViews() {
        final BaseViewAnimator appnameAnima = new FlipInXAnimator();
        appnameAnima.setDuration(BaseViewAnimator.DURATION / 2);
        appnameAnima.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                try {
                    Thread.sleep(1000);
                    startFragment(new FragmentRequest(FragmentType.HOME, FragmentHome.class, false, false, null));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        BaseViewAnimator logoAnim = new StandUpAnimator();
        logoAnim.setDuration(BaseViewAnimator.DURATION);
        logoAnim.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                appnameView.setVisibility(View.VISIBLE);
                YoYo.with(appnameAnima).playOn(appnameView);
            }
        });
        YoYo.with(logoAnim).playOn(logoView);
    }
}

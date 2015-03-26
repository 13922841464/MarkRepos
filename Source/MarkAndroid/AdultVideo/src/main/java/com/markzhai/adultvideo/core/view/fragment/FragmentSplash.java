package com.markzhai.adultvideo.core.view.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.attention.FlashAnimator;
import com.markzhai.adultvideo.BuildConfig;
import com.markzhai.adultvideo.R;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.framework.page.FragmentType;
import com.markzhai.library.utils.AnimationUtils;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/3/23.
 */
public class FragmentSplash extends BaseFragment {

    @InjectView(R.id.splash_text)
    private TextView splashText;

    @InjectView(R.id.enter_button)
    private Button enterButton;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void init() {
        AnimationUtils.setAnimation(splashText, Techniques.Flash, BuildConfig.DEBUG ? 1 : 2000, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                AnimationUtils.setAnimation(enterButton, Techniques.FadeIn, BuildConfig.DEBUG ? 1 : 400);
                enterButton.setVisibility(View.VISIBLE);
            }
        });

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentRequest homeRequest = new FragmentRequest(FragmentType.HOME, FragmentHome.class, false, false, null);
                startFragment(homeRequest);
            }
        });
    }
}

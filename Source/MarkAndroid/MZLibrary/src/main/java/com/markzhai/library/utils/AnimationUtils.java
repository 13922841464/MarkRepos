package com.markzhai.library.utils;

import android.view.View;

import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

/**
 * Created by marktlzhai on 2015/3/26.
 */
public class AnimationUtils {

    public static void setAnimation(View view, BaseViewAnimator animator) {
        setAnimation(view, animator, animator.getDuration());
    }

    public static void setAnimation(View view, BaseViewAnimator animator, long duration) {
        setAnimation(view, animator, duration, null);
    }

    public static void setAnimation(View view, BaseViewAnimator animator, long duration, Animator.AnimatorListener listener) {
        if (listener != null) {
            animator.addAnimatorListener(listener);
        }
        YoYo.with(animator).duration(duration).playOn(view);
    }

    public static void setAnimation(View view, Techniques techniques) {
        setAnimation(view, techniques.getAnimator());
    }

    public static void setAnimation(View view, Techniques techniques, long duration) {
        setAnimation(view, techniques.getAnimator(), duration);
    }

    public static void setAnimation(View view, Techniques techniques, long duration, Animator.AnimatorListener listener) {
        setAnimation(view, techniques.getAnimator(), duration, listener);
    }
}

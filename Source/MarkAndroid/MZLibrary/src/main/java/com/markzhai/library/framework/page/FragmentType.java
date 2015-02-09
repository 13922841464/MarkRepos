package com.markzhai.library.framework.page;

import com.markzhai.library.R;

/**
 * Created by marktlzhai on 2015/1/4.
 */
public enum FragmentType {

    HOME(R.anim.fade_in, R.anim.fade_out, 0, 0), APP(R.anim.push_right_in, R.anim.push_left_out, R.anim.push_left_in, R.anim.push_right_out), EDIT(R.anim.slide_in_from_bottom, R.anim.slide_out_to_top, R.anim.slide_in_from_top,
            R.anim.slide_out_to_bottom);

    private int enterAnim;
    private int exitAnim;
    private int popEnterAnim;
    private int popExitAnim;

    private FragmentType(int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim) {
        this.enterAnim = enterAnim;
        this.exitAnim = exitAnim;
        this.popEnterAnim = popEnterAnim;
        this.popExitAnim = popExitAnim;
    }

    public int getEnterAnim() {
        return enterAnim;
    }

    public void setEnterAnim(int enterAnim) {
        this.enterAnim = enterAnim;
    }

    public int getExitAnim() {
        return exitAnim;
    }

    public void setExitAnim(int exitAnim) {
        this.exitAnim = exitAnim;
    }

    public int getPopEnterAnim() {
        return popEnterAnim;
    }

    public void setPopEnterAnim(int popEnterAnim) {
        this.popEnterAnim = popEnterAnim;
    }

    public int getPopExitAnim() {
        return popExitAnim;
    }

    public void setPopExitAnim(int popExitAnim) {
        this.popExitAnim = popExitAnim;
    }
}

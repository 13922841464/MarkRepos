package com.markzhai.adultol.model;

import com.markzhai.adultol.views.HomeFragment;
import com.markzhai.adultol.views.LoginFragment;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.framework.page.FragmentType;

/**
 * Created by marktlzhai on 2015/2/5.
 */
public class Redirect {

    /**
     * 主界面
     */
    public static final FragmentRequest HOME;

    /**
     * 登陆界面
     */
    public static final FragmentRequest LOGIN;

    static {
        HOME = new FragmentRequest(FragmentType.APP, HomeFragment.class, true, false, null);
        LOGIN = new FragmentRequest(FragmentType.APP, LoginFragment.class, false, false, null);
    }
}

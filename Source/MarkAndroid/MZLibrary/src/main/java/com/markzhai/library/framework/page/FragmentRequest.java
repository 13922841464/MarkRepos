package com.markzhai.library.framework.page;

import android.os.Bundle;

import com.markzhai.library.framework.BaseFragment;

/**
 * Created by marktlzhai on 2015/1/4.
 */
public class FragmentRequest {

    private FragmentType fragmentType = FragmentType.APP;

    private Class<? extends BaseFragment> fragmentClass;

    private boolean addBackStack = true;

    private boolean fullScreen = false;

    private Bundle data = new Bundle();

    public FragmentRequest(Class<? extends BaseFragment> fragmentClass) {
        this(FragmentType.APP, fragmentClass);
    }

    public FragmentRequest(FragmentType fragmentType, Class<? extends BaseFragment> fragmentClass) {
        this(FragmentType.APP, fragmentClass, null);
    }

    public FragmentRequest(FragmentType fragmentType, Class<? extends BaseFragment> fragmentClass, Bundle data) {
        this(FragmentType.APP, fragmentClass, false, data);
    }

    public FragmentRequest(FragmentType fragmentType, Class<? extends BaseFragment> fragmentClass, boolean fullScreen, Bundle data) {
        this(FragmentType.APP, fragmentClass, true, false, data);
    }

    public FragmentRequest(FragmentType fragmentType, Class<? extends BaseFragment> fragmentClass, boolean addBackStack, boolean fullScreen, Bundle data) {
        this.fragmentType = fragmentType;
        this.fragmentClass = fragmentClass;
        this.addBackStack = addBackStack;
        this.fullScreen = fullScreen;
        this.data = new Bundle();

        if (data != null) {
            this.data.putAll(data);
        }
    }

    public FragmentType getFragmentType() {
        return fragmentType;
    }

    public void setFragmentType(FragmentType fragmentType) {
        this.fragmentType = fragmentType;
    }

    public Class<? extends BaseFragment> getFragmentClass() {
        return fragmentClass;
    }

    public void setFragmentClass(Class<? extends BaseFragment> fragmentClass) {
        this.fragmentClass = fragmentClass;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public Bundle getData() {
        return data;
    }

    public void setData(Bundle data) {
        this.data = data;
    }

    public boolean addBackStack() {
        return addBackStack;
    }

    public void setAddBackStack(boolean addBackStack) {
        this.addBackStack = addBackStack;
    }
}

package com.markzhai.mediagather.core.view;

import com.markzhai.library.framework.BaseActivity;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.framework.page.FragmentType;
import com.markzhai.mediagather.core.view.fragment.FragmentSplash;

public class MainActivity extends BaseActivity {
    @Override
    protected FragmentRequest installHome() {
        FragmentRequest request = new FragmentRequest(FragmentType.HOME, FragmentSplash.class, false, true, null);
        return request;
    }
}
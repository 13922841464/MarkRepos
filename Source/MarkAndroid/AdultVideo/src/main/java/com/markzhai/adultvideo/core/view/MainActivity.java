package com.markzhai.adultvideo.core.view;

import com.markzhai.adultvideo.core.view.fragment.FragmentSplash;
import com.markzhai.library.framework.BaseActivity;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.framework.page.FragmentType;

public class MainActivity extends BaseActivity {

    @Override
    protected FragmentRequest installHome() {
        FragmentRequest request = new FragmentRequest(FragmentType.HOME, FragmentSplash.class, false, true, getIntent().getExtras());
        return request;
    }
}

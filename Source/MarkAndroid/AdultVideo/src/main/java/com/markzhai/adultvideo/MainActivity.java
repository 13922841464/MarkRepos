package com.markzhai.adultvideo;

import com.markzhai.adultvideo.fragment.FragmentHome;
import com.markzhai.library.framework.BaseActivity;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.framework.page.FragmentType;


public class MainActivity extends BaseActivity {

    @Override
    protected FragmentRequest installHome() {
        return new FragmentRequest(FragmentType.HOME, FragmentHome.class, false, true, null);
    }
}

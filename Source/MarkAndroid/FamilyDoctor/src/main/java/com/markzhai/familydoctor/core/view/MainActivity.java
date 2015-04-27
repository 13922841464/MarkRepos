package com.markzhai.familydoctor.core.view;

import com.markzhai.familydoctor.core.view.fragment.FragmentSplash;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.framework.page.FragmentType;
import com.markzhai.talkingdata.TalkDataActivity;

public class MainActivity extends TalkDataActivity {

    @Override
    protected FragmentRequest installHome() {
        FragmentRequest request = new FragmentRequest(FragmentType.HOME, FragmentSplash.class, false, true, getIntent().getExtras());
        return request;
    }
}

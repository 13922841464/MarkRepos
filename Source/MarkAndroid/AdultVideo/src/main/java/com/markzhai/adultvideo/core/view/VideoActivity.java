package com.markzhai.adultvideo.core.view;

import com.markzhai.adultvideo.core.view.fragment.FragmentVideo;
import com.markzhai.library.framework.BaseActivity;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.framework.page.FragmentType;

/**
 * Created by marktlzhai on 2015/3/31.
 */
public class VideoActivity extends BaseActivity {

    @Override
    protected FragmentRequest installHome() {
        FragmentRequest request = new FragmentRequest(FragmentType.HOME, FragmentVideo.class, false, true, getIntent().getExtras());
        return request;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

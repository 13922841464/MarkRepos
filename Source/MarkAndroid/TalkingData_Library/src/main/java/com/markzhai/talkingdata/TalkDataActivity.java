package com.markzhai.talkingdata;

import com.markzhai.library.framework.BaseActivity;
import com.tendcloud.tenddata.TCAgent;

/**
 * Created by marktlzhai on 2015/4/16.
 */
public abstract class TalkDataActivity extends BaseActivity {

    @Override
    protected void onResume() {
        super.onResume();
        TCAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        TCAgent.onPause(this);
    }
}

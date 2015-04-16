package com.markzhai.talkingdata;

import com.markzhai.library.framework.BaseFragment;
import com.tendcloud.tenddata.TCAgent;

/**
 * Created by marktlzhai on 2015/4/16.
 */
public abstract class TalkingDataFragment extends BaseFragment {
    @Override
    public void onResume() {
        super.onResume();
        TCAgent.onPageStart(getBaseActivity(), getFragmentTag());
    }

    @Override
    public void onPause() {
        super.onPause();
        TCAgent.onPageEnd(getBaseActivity(), getFragmentTag());
    }
}

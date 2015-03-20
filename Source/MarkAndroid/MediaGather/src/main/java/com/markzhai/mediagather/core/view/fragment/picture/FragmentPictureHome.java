package com.markzhai.mediagather.core.view.fragment.picture;

import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.widget.MZTopbar;
import com.markzhai.mediagather.R;
import com.markzhai.mediagather.core.Constants;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/3/18.
 */
public class FragmentPictureHome extends BaseFragment {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_picture_home;
    }

    @Override
    public void init() {
    }

    @Override
    public void initTopbar(MZTopbar topbar) {
        super.initTopbar(topbar);
        topbar.setTitle(R.string.category_picture);
        topbar.setIcon(0, null);
        topbar.setMenu(0, null);
    }
}

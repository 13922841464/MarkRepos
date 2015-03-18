package com.markzhai.mediagather.core.view.fragment.video;

import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.widget.MZTopbar;
import com.markzhai.mediagather.R;

/**
 * Created by marktlzhai on 2015/3/18.
 */
public class FragmentMovieHome extends BaseFragment {
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_video_home;
    }

    @Override
    public void init() {

    }

    @Override
    public void initTopbar(MZTopbar topbar) {
        topbar.setTitle(R.string.category_movie);
        topbar.setIcon(R.drawable.category_movie);
    }
}

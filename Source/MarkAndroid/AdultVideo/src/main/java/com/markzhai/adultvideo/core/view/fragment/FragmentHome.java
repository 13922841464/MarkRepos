package com.markzhai.adultvideo.core.view.fragment;

import android.view.View;

import com.markzhai.adultvideo.R;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.widget.MZTopbar;

/**
 * Created by marktlzhai on 2015/3/26.
 */
public class FragmentHome extends BaseFragment {
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initTopbar(MZTopbar topbar) {
        super.initTopbar(topbar);

        

        topbar.setIcon(R.drawable.ic_launcher, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("icon");
            }
        });

        topbar.setMenu(R.drawable.filter, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("filter");
            }
        });
    }

    @Override
    public void init() {

    }
}

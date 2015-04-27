package com.markzhai.familydoctor.core.view.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.markzhai.familydoctor.R;

/**
 * Created by marktlzhai on 2015/4/24.
 */
public class LeftDrawer extends RelativeLayout {
    public LeftDrawer(Context context) {
        super(context);
        init();
    }

    public LeftDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LeftDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_left_drawer, null, false);
        addView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}

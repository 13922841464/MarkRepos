package com.markzhai.healthkeeper.core.view.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.markzhai.healthkeeper.R;

/**
 * Created by marktlzhai on 2015/4/14.
 */
public class LeftDrawerView extends RelativeLayout {
    public LeftDrawerView(Context context) {
        super(context);
        initView();
    }

    public LeftDrawerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LeftDrawerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.view_left_drawer, null, false);
        addView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}

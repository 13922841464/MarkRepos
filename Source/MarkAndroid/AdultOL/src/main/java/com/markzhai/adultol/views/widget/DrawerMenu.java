package com.markzhai.adultol.views.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.markzhai.adultol.R;

/**
 * Created by marktlzhai on 2015/2/12.
 */
public class DrawerMenu extends RelativeLayout {
    public DrawerMenu(Context context) {
        super(context);
        initView();
    }

    public DrawerMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DrawerMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_drawer, null, false);
        addView(rootView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}

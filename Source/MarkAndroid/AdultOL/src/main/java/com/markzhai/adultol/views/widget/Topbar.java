package com.markzhai.adultol.views.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.markzhai.adultol.R;

/**
 * Created by marktlzhai on 2015/2/13.
 */
public class Topbar extends RelativeLayout {

    private ImageView icon;
    private TextView title;
    private ImageView leftButton;
    private ImageView rightButton;

    public Topbar(Context context) {
        super(context);
        initView();
    }

    public Topbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public Topbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_topbar, null, false);

        icon = (ImageView) rootView.findViewById(R.id.topbar_icon);
        title = (TextView) rootView.findViewById(R.id.topbar_title);
        leftButton = (ImageView) rootView.findViewById(R.id.topbar_btn_left);
        leftButton.setVisibility(View.GONE);
        rightButton = (ImageView) rootView.findViewById(R.id.topbar_btn_right);
        rightButton.setVisibility(View.GONE);

        addView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}

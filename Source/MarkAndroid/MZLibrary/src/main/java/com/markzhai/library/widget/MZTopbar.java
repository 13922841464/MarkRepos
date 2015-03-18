package com.markzhai.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.markzhai.library.R;

/**
 * Created by marktlzhai on 2015/3/18.
 */
public class MZTopbar extends RelativeLayout {

    private TextView titleView;

    private ImageView iconView;

    public MZTopbar(Context context) {
        super(context);
        initViews();
    }

    public MZTopbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public MZTopbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_topbar, null, false);

        titleView = (TextView) rootView.findViewById(R.id.topbar_title);
        iconView = (ImageView) rootView.findViewById(R.id.topbar_icon);
        iconView.setImageResource(0);

        addView(rootView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    public void setTitle(int stringRes) {
        titleView.setText(stringRes);
    }

    public void setTitle(String title) {
        titleView.setText(title);
    }

    public void setIcon(int iconRes) {
        iconView.setImageResource(iconRes);
    }
}

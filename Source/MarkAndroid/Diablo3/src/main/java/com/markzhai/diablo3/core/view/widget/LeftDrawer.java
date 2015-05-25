package com.markzhai.diablo3.core.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.markzhai.diablo3.R;

/**
 * Created by marktlzhai on 2015/5/25.
 */
public class LeftDrawer extends RelativeLayout implements View.OnClickListener {

    public interface DrawerClickCallback {
        void jobItemClicked();

        void levelItemClicked();

        void heroItemClicked();

        void diabloDBItemClicked();
    }

    private TextView jobItem;
    private TextView levelItem;
    private TextView heroItem;
    private TextView diabloDBItem;

    private DrawerClickCallback clickCallback;

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

    public void setClickCallback(DrawerClickCallback clickCallback) {
        this.clickCallback = clickCallback;
    }

    private void init() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_left_drawer, null, false);

        jobItem = (TextView) rootView.findViewById(R.id.drawer_job);
        levelItem = (TextView) rootView.findViewById(R.id.drawer_level_top);
        heroItem = (TextView) rootView.findViewById(R.id.drawer_hero_top);
        diabloDBItem = (TextView) rootView.findViewById(R.id.drawer_diablo_db);

        jobItem.setOnClickListener(this);
        levelItem.setOnClickListener(this);
        heroItem.setOnClickListener(this);
        diabloDBItem.setOnClickListener(this);

        addView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onClick(View view) {
        if (clickCallback == null) {
            return;
        }

        int clickID = view.getId();

        switch (clickID) {
            case R.id.drawer_job:
                clickCallback.jobItemClicked();
                break;
            case R.id.drawer_level_top:
                clickCallback.levelItemClicked();
                break;
            case R.id.drawer_hero_top:
                clickCallback.heroItemClicked();
                break;
            case R.id.drawer_diablo_db:
                clickCallback.diabloDBItemClicked();
                break;
        }
    }
}

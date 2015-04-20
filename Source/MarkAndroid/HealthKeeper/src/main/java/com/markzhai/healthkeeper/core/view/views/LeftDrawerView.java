package com.markzhai.healthkeeper.core.view.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.markzhai.healthkeeper.R;

/**
 * Created by marktlzhai on 2015/4/14.
 */
public class LeftDrawerView extends RelativeLayout {

    private ExpandableListView drawerList;
    private DrawerListAdapter drawerListAdapter;

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

        drawerList = (ExpandableListView) rootView.findViewById(R.id.drawer_list);
        drawerListAdapter = new DrawerListAdapter();
        drawerList.setAdapter(drawerListAdapter);

        addView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    class DrawerListAdapter extends BaseExpandableListAdapter {

        @Override
        public int getGroupCount() {
            return 0;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 0;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return null;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}

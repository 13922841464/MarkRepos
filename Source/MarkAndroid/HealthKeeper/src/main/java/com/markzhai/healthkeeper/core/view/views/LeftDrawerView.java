package com.markzhai.healthkeeper.core.view.views;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.markzhai.healthkeeper.R;
import com.markzhai.healthkeeper.core.model.DrawerModel;
import com.markzhai.library.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marktlzhai on 2015/4/14.
 */
public class LeftDrawerView extends RelativeLayout {

    public static interface DrawerClickListener {
        void medicalCenterClicked();

        void medicalCorpClicked();

        void healthKnowledgeClicked();

        void healthFoodsClicked();

        void feedbackClicked();

        void shareClicked();

        void settingClicked();

        void aboutClicked();
    }

    private ListView drawerList;
    private DrawerListAdapter drawerListAdapter;

    private List<DrawerModel> drawerListDatas;

    private DrawerClickListener clickListener;

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

        rootView.findViewById(R.id.button_about).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.aboutClicked();
                }
            }
        });

        rootView.findViewById(R.id.button_share).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.shareClicked();
                }
            }
        });

        rootView.findViewById(R.id.button_setting).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.settingClicked();
                }
            }
        });

        rootView.findViewById(R.id.button_feedback).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.feedbackClicked();
                }
            }
        });

        loadDrawerList();
        drawerList = (ListView) rootView.findViewById(R.id.drawer_list);
        drawerListAdapter = new DrawerListAdapter();
        drawerList.setAdapter(drawerListAdapter);

        addView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void setClickListener(DrawerClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private void loadDrawerList() {
        drawerListDatas = new ArrayList<DrawerModel>();

        //医疗中心
        drawerListDatas.add(new DrawerModel(R.drawable.icon_medical_center_normal, R.string.category_medical_center, true));
        //医院药店
        drawerListDatas.add(new DrawerModel(R.drawable.icon_medical_corp_normal, R.string.category_medical_corp, true));
        //健康知识
        drawerListDatas.add(new DrawerModel(R.drawable.icon_health_knowledge_normal, R.string.category_health_knowledge, true));
        //药品食品
        drawerListDatas.add(new DrawerModel(R.drawable.icon_health_food_normal, R.string.category_health_foods, true));
    }

    class DrawerListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        public DrawerListAdapter() {
            layoutInflater = LayoutInflater.from(getContext());
        }

        @Override
        public int getCount() {
            return drawerListDatas.size();
        }

        @Override
        public DrawerModel getItem(int position) {
            return drawerListDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).drawerIcon;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.drawer_list_item, parent, false);

            ImageView iconView = (ImageView) convertView.findViewById(R.id.drawer_list_icon);
            TextView nameView = (TextView) convertView.findViewById(R.id.drawer_list_name);
            ImageView arrowView = (ImageView) convertView.findViewById(R.id.drawer_list_arrow);

            DrawerModel item = getItem(position);
            iconView.setImageResource(item.drawerIcon);
            nameView.setText(item.drawerNameRes);
            arrowView.setVisibility(item.hasArrow ? View.VISIBLE : View.GONE);

            convertView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (LeftDrawerView.this.clickListener != null) {
                        int iconID = (int) getItemId(position);
                        switch (iconID) {
                            case R.drawable.icon_medical_center_normal:
                                LeftDrawerView.this.clickListener.medicalCenterClicked();
                                break;
                            case R.drawable.icon_medical_corp_normal:
                                LeftDrawerView.this.clickListener.medicalCorpClicked();
                                break;
                            case R.drawable.icon_health_knowledge_normal:
                                LeftDrawerView.this.clickListener.healthKnowledgeClicked();
                                break;
                            case R.drawable.icon_health_food_normal:
                                LeftDrawerView.this.clickListener.healthFoodsClicked();
                                break;
                        }
                    }
                }
            });

            return convertView;
        }
    }
}

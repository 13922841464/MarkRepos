package com.markzhai.familydoctor.core.view.weiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.markzhai.familydoctor.R;

import java.util.List;

/**
 * Created by marktlzhai on 2015/4/24.
 */
public class LeftDrawer extends RelativeLayout {

    public interface DrawerClickListener {
        void medicalCenterClick();

        void foodsClick();

        void hospitalClick();

        void questionClick();

        void feedbackClick();

        void aboutClick();
    }

    private ListView drawerList;
    private DrawerListAdapter drawerListAdapter;

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

    public void setListener(final DrawerClickListener listener) {
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DrawerType item = (DrawerType) adapterView.getItemAtPosition(i);
                switch (item) {
                    case MedicalCenter:
                        listener.medicalCenterClick();
                        break;
                    case Foods:
                        listener.foodsClick();
                        break;
                    case Hospital:
                        listener.hospitalClick();
                        break;
                    case Question:
                        listener.questionClick();
                        break;
                    case Feedback:
                        listener.feedbackClick();
                        break;
                    case About:
                        listener.aboutClick();
                        break;
                }
            }
        });
    }

    private void init() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_left_drawer, null, false);

        drawerList = (ListView) rootView.findViewById(R.id.drawer_list);
        drawerListAdapter = new DrawerListAdapter();
        drawerList.setAdapter(drawerListAdapter);

        addView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    class DrawerListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        private DrawerType[] drawerTypes;

        public DrawerListAdapter() {
            layoutInflater = LayoutInflater.from(getContext());
            drawerTypes = DrawerType.values();
        }

        @Override
        public int getCount() {
            return drawerTypes.length;
        }

        @Override
        public DrawerType getItem(int i) {
            return drawerTypes[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = layoutInflater.inflate(R.layout.item_drawer, viewGroup, false);

            final DrawerType item = getItem(i);
            ImageView icon = (ImageView) view.findViewById(R.id.drawer_item_icon);
            TextView name = (TextView) view.findViewById(R.id.drawer_item_name);

            icon.setImageResource(item.iconRes);
            name.setText(item.nameRes);
            return view;
        }
    }

    public enum DrawerType {
        //医疗中心
        MedicalCenter(R.drawable.drawer_medical_center, R.string.drawer_medical_center),
        //食品药品
        Foods(R.drawable.drawer_foods, R.string.drawer_foods),
        //医院药店
        Hospital(R.drawable.drawer_hospical, R.string.drawer_hospital),
        //健康提问
        Question(R.drawable.drawer_question, R.string.drawer_question),
        //用户反馈
        Feedback(R.drawable.drawer_feedback, R.string.drawer_feedback),
        //关于
        About(R.drawable.drawer_about, R.string.drawer_about);

        private int iconRes;

        private int nameRes;

        private DrawerType(int iconRes, int nameRes) {
            this.nameRes = nameRes;
            this.iconRes = iconRes;
        }
    }
}

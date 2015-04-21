package com.markzhai.healthkeeper.core.view.fragment;

import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.markzhai.gdt.AdAdapter;
import com.markzhai.gdt.GDT;
import com.markzhai.healthkeeper.R;
import com.markzhai.healthkeeper.core.App;
import com.markzhai.healthkeeper.core.model.HomeItemModel;
import com.markzhai.healthkeeper.core.view.fragment.medicalcenter.FragmentCheckItems;
import com.markzhai.healthkeeper.core.view.fragment.medicalcenter.FragmentDiseaseFinder;
import com.markzhai.healthkeeper.core.view.fragment.medicalcenter.FragmentDiseaseInformation;
import com.markzhai.healthkeeper.core.view.fragment.medicalcenter.FragmentSurgeryItems;
import com.markzhai.healthkeeper.core.view.views.LeftDrawerView;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.widget.MZTopbar;
import com.markzhai.talkingdata.TalkingDataFragment;
import com.qq.e.ads.AdListener;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/4/14.
 */
public class FragmentHome extends TalkingDataFragment {

    @InjectView(R.id.home_drawer)
    private DrawerLayout drawer;
    @InjectView(R.id.left_drawer)
    private LeftDrawerView leftDrawerView;

    @InjectView(R.id.main_page_list)
    private ListView mainPageList;
    private HomeListAdapter homeListAdapter;
    private List<HomeItemModel> homeListItems;

    private DrawerListener drawerListener;

    @InjectView(R.id.home_ad_view)
    private FrameLayout adViewLayout;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        getBaseActivity().setDrawer(drawer, Gravity.START);
        drawerListener = new DrawerListener();
        leftDrawerView.setClickListener(drawerListener);

        homeListAdapter = new HomeListAdapter();
        mainPageList.setAdapter(homeListAdapter);

        GDT.showBannerAD(App.getBaseActivity(), adViewLayout, App.GDT_APPID, App.GDT_POSID_BANNER, null);
    }

    @Override
    public void initTopbar(final MZTopbar topbar) {
        super.initTopbar(topbar);
        topbar.setTitle(R.string.category_medical_center);
        topbar.setIcon(R.drawable.icon_medical_center_normal, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDrawer();
            }
        });
        topbar.setMenu(0, null);
    }

    private void toggleDrawer() {
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START);
        } else {
            drawer.openDrawer(Gravity.START);
        }
    }

    private void closeDrawer() {
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START);
        }
    }

    class HomeListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        public HomeListAdapter() {
            layoutInflater = LayoutInflater.from(App.getBaseActivity());
            homeListItems = new ArrayList<HomeItemModel>();
            update(MEDICAL_CENTER_LIST);
        }

        private void update(List<HomeItemModel> data) {
            homeListItems.clear();
            homeListItems.addAll(data);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return homeListItems.size();
        }

        @Override
        public HomeItemModel getItem(int position) {
            return homeListItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.home_list_item, parent, false);

            final HomeItemModel item = getItem(position);

            ImageView itemIconView = (ImageView) convertView.findViewById(R.id.home_item_icon);
            itemIconView.setImageResource(item.iconRes);
            TextView itemNameView = (TextView) convertView.findViewById(R.id.home_item_name);
            itemNameView.setText(item.nameRes);
            TextView itemDescView = (TextView) convertView.findViewById(R.id.home_item_desc);
            itemDescView.setText(item.descRes);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startFragment(item.actionRequest);
                }
            });

            return convertView;
        }
    }

    class DrawerListener implements LeftDrawerView.DrawerClickListener {

        @Override
        public void medicalCenterClicked() {
            closeDrawer();
            topbar.setTitle(R.string.category_medical_center);
            topbar.setIcon(R.drawable.icon_medical_center_normal, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleDrawer();
                }
            });
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    homeListAdapter.update(MEDICAL_CENTER_LIST);
                }
            }, 200);
        }

        @Override
        public void medicalCorpClicked() {
            closeDrawer();
            topbar.setTitle(R.string.category_medical_corp);
            topbar.setIcon(R.drawable.icon_medical_corp_normal, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleDrawer();
                }
            });
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    homeListAdapter.update(MEDICAL_CORP_LIST);
                }
            }, 200);
        }

        @Override
        public void healthKnowledgeClicked() {
            closeDrawer();
            topbar.setTitle(R.string.category_health_knowledge);
            topbar.setIcon(R.drawable.icon_health_knowledge_normal, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleDrawer();
                }
            });
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    homeListAdapter.update(HEALTH_KNOWLEDGE_LIST);
                }
            }, 200);
        }

        @Override
        public void healthFoodsClicked() {
            closeDrawer();
            topbar.setTitle(R.string.category_health_foods);
            topbar.setIcon(R.drawable.icon_health_food_normal, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleDrawer();
                }
            });
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    homeListAdapter.update(HEALTH_FOOD_LIST);
                }
            }, 200);
        }

        @Override
        public void feedbackClicked() {
            showToast("medicalCenterClicked");
        }

        @Override
        public void shareClicked() {
            showToast("medicalCenterClicked");
        }

        @Override
        public void settingClicked() {
            showToast("medicalCenterClicked");
        }

        @Override
        public void aboutClicked() {
            closeDrawer();
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    startFragment(FragmentAbout.class);
                }
            }, 200);
        }
    }

    //医疗中心
    private static final List<HomeItemModel> MEDICAL_CENTER_LIST = new ArrayList<HomeItemModel>();
    //医院药店
    private static final List<HomeItemModel> MEDICAL_CORP_LIST = new ArrayList<HomeItemModel>();
    //健康知识
    private static final List<HomeItemModel> HEALTH_KNOWLEDGE_LIST = new ArrayList<HomeItemModel>();
    //药品食品
    private static final List<HomeItemModel> HEALTH_FOOD_LIST = new ArrayList<HomeItemModel>();

    static {
        //医疗中心
        MEDICAL_CENTER_LIST.add(new HomeItemModel(R.drawable.disease_information, R.string.disease_information, R.string.disease_information_hint, new FragmentRequest(FragmentDiseaseInformation.class)));
        MEDICAL_CENTER_LIST.add(new HomeItemModel(R.drawable.disease_finder, R.string.disease_finder, R.string.disease_finder_hint, new FragmentRequest(FragmentDiseaseFinder.class)));
        MEDICAL_CENTER_LIST.add(new HomeItemModel(R.drawable.check_items, R.string.check_items, R.string.check_items_hint, new FragmentRequest(FragmentCheckItems.class)));
        MEDICAL_CENTER_LIST.add(new HomeItemModel(R.drawable.surgery_items, R.string.surgery_items, R.string.surgery_items_hint, new FragmentRequest(FragmentSurgeryItems.class)));

        //医院药店
        MEDICAL_CORP_LIST.add(new HomeItemModel(R.drawable.hospital_information, R.string.hospital_information, R.string.hospital_information_hint, new FragmentRequest(FragmentSurgeryItems.class)));
        MEDICAL_CORP_LIST.add(new HomeItemModel(R.drawable.store_information, R.string.store_information, R.string.store_information_hint, new FragmentRequest(FragmentSurgeryItems.class)));
        MEDICAL_CORP_LIST.add(new HomeItemModel(R.drawable.factory_information, R.string.factory_information, R.string.factory_information_hint, new FragmentRequest(FragmentSurgeryItems.class)));

        //健康知识
        HEALTH_KNOWLEDGE_LIST.add(new HomeItemModel(R.drawable.health_knowledge, R.string.health_knowledge, R.string.health_knowledge_hint, new FragmentRequest(FragmentSurgeryItems.class)));
        HEALTH_KNOWLEDGE_LIST.add(new HomeItemModel(R.drawable.health_ask, R.string.health_ask, R.string.health_ask_hint, new FragmentRequest(FragmentSurgeryItems.class)));
        HEALTH_KNOWLEDGE_LIST.add(new HomeItemModel(R.drawable.health_book, R.string.health_book, R.string.health_book_hint, new FragmentRequest(FragmentSurgeryItems.class)));

        //药品食品
        HEALTH_FOOD_LIST.add(new HomeItemModel(R.drawable.health_foods, R.string.health_foods, R.string.health_foods_hint, new FragmentRequest(FragmentSurgeryItems.class)));
        HEALTH_FOOD_LIST.add(new HomeItemModel(R.drawable.health_diet, R.string.health_diet, R.string.health_diet_hint, new FragmentRequest(FragmentSurgeryItems.class)));
        HEALTH_FOOD_LIST.add(new HomeItemModel(R.drawable.health_drug, R.string.health_drug, R.string.health_drug_hint, new FragmentRequest(FragmentSurgeryItems.class)));
    }
}

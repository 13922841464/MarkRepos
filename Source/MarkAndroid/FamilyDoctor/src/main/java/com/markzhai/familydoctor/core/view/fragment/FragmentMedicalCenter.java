package com.markzhai.familydoctor.core.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.markzhai.familydoctor.R;
import com.markzhai.familydoctor.core.view.fragment.medicacenter.FragmentCheckingItem;
import com.markzhai.familydoctor.core.view.fragment.medicacenter.FragmentDiseaseInfo;
import com.markzhai.familydoctor.core.view.fragment.medicacenter.FragmentSurgeryItem;
import com.markzhai.familydoctor.core.view.fragment.medicacenter.FragmentSymptomsFind;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.widget.MZTopbar;

import roboguice.inject.InjectView;

/**
 * 医疗中心界面
 * Created by marktlzhai on 2015/4/28.
 */
public class FragmentMedicalCenter extends BaseFragment {

    @InjectView(R.id.medical_center_function_list)
    private ListView functionList;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_medical_center;
    }

    @Override
    public void init() {
        MedicalCenterAdapter adapter = new MedicalCenterAdapter();
        functionList.setAdapter(adapter);
    }

    @Override
    public void initTopbar(MZTopbar topbar) {
        super.initTopbar(topbar);
        topbar.setTitle(R.string.drawer_medical_center);
        topbar.setIcon(R.drawable.drawer_medical_center, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBaseActivity().onBackPressed();
            }
        });
    }

    @Override
    public void initData() {

    }

    enum MedicalCenterType {
        //疾病信息
        DiseaseInfo(R.string.medical_disease_info, R.string.medical_disease_info_desc, FragmentDiseaseInfo.class),
        //病状查找
        SymptomsFind(R.string.medical_symptoms_find, R.string.medical_symptoms_find_desc, FragmentSymptomsFind.class),
        //检查项目
        CheckingItem(R.string.medical_checking_item, R.string.medical_checking_item_desc, FragmentCheckingItem.class),
        //手术项目
        SurgeryItem(R.string.medical_surgery_item, R.string.medical_surgery_item_desc, FragmentSurgeryItem.class);

        private int name;
        private int desc;
        private Class<? extends BaseFragment> clazz;

        private MedicalCenterType(int name, int desc, Class<? extends BaseFragment> clazz) {
            this.name = name;
            this.desc = desc;
            this.clazz = clazz;
        }
    }

    class MedicalCenterAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        public MedicalCenterAdapter() {
            layoutInflater = LayoutInflater.from(getBaseActivity());
        }

        @Override
        public int getCount() {
            return MedicalCenterType.values().length;
        }

        @Override
        public MedicalCenterType getItem(int position) {
            return MedicalCenterType.values()[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.item_medical_center, parent, false);

            final MedicalCenterType item = getItem(position);

            TextView nameView = (TextView) convertView.findViewById(R.id.medical_center_name);
            nameView.setText(item.name);
            TextView descView = (TextView) convertView.findViewById(R.id.medical_center_desc);
            descView.setText(item.desc);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startFragment(item.clazz);
                }
            });

            return convertView;
        }
    }
}

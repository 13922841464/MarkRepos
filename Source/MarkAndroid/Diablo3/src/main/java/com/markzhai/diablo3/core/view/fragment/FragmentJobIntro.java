package com.markzhai.diablo3.core.view.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.markzhai.diablo3.R;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.widget.MZTopbar;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/5/25.
 */
public class FragmentJobIntro extends BaseFragment {

    @InjectView(R.id.job_grid)
    private GridView jobGrid;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_job_intro;
    }

    @Override
    public void initTopbar(MZTopbar topbar) {
        super.initTopbar(topbar);
        topbar.setTitle(R.string.job_title);
        topbar.setIcon(R.drawable.icon_left, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void init() {
        jobGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                JobType item = (JobType) adapterView.getItemAtPosition(i);
                showToast(item.name);
            }
        });
    }

    @Override
    public void initData() {
        jobGrid.setAdapter(new JobAdapter());
    }

    class JobAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return JobType.values().length;
        }

        @Override
        public JobType getItem(int i) {
            return JobType.values()[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            JobType item = getItem(i);

            TextView textView = new TextView(getBaseActivity(), null, R.style.font_body);
            textView.setText(item.name);
            return textView;
        }
    }

    enum JobType {
        Barbarian(R.string.Barbarian, R.drawable.icon_barbarian),
        WitchDoctor(R.string.WitchDoctor, R.drawable.icon_witch_doctor),
        Wizard(R.string.Wizard, R.drawable.icon_wizard),
        Monk(R.string.Monk, R.drawable.icon_monk),
        DemonHunter(R.string.DemonHunter, R.drawable.icon_demon_hunter),
        Crusader(R.string.Crusader, R.drawable.icon_crusader);

        private int name;
        private int icon;

        private JobType(int name, int icon) {
            this.name = name;
            this.icon = icon;
        }
    }
}

package com.markzhai.familydoctor.core.view.fragment.medicacenter;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.markzhai.familydoctor.R;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.utils.StringUtils;
import com.markzhai.library.widget.MZTopbar;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 15-5-14.
 */
public class FragmentDiseaseInfo extends BaseFragment {

    @InjectView(R.id.disease_search_result_grid)
    private GridView diseaseSearchResultView;

    @InjectView(R.id.disease_search_input)
    private EditText diseaseSearchInput;

    @InjectView(R.id.disease_search_button)
    private Button diseaseSearchButton;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_disease_info;
    }

    @Override
    public void init() {
        diseaseSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(diseaseSearchInput.getText().toString().trim())) {

                }
            }
        });
    }

    @Override
    public void initTopbar(MZTopbar topbar) {
        super.initTopbar(topbar);
        topbar.setTitle(R.string.medical_disease_info);
        topbar.setIcon(R.drawable.topbar_menu, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBaseActivity().onBackPressed();
            }
        });
    }

    @Override
    public void initData() {

    }
}

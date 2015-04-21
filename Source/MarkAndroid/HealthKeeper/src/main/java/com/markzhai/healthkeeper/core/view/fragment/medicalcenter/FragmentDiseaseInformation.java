package com.markzhai.healthkeeper.core.view.fragment.medicalcenter;

import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import com.markzhai.healthkeeper.R;
import com.markzhai.healthkeeper.core.App;
import com.markzhai.healthkeeper.core.controller.medicalcenter.MedicalCenterController;
import com.markzhai.healthkeeper.core.model.medicalcenter.DepartmentModel;
import com.markzhai.healthkeeper.core.model.medicalcenter.PlaceModel;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.utils.CollectionUtils;
import com.markzhai.library.widget.MZTopbar;
import com.wefika.flowlayout.FlowLayout;

import java.text.MessageFormat;
import java.util.List;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/4/21.
 */
public class FragmentDiseaseInformation extends BaseFragment implements MedicalCenterController.LoadPlaceCallback, MedicalCenterController.LoadDepartmentCallback {

    @InjectView(R.id.department_flow)
    private FlowLayout departmentFlow;

    @InjectView(R.id.place_flow)
    private FlowLayout placeFlow;

    @InjectView(R.id.department_filter)
    private Button departmentFilterButton;
    @InjectView(R.id.place_filter)
    private Button placeFilterButton;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_disease_info;
    }

    @Override
    public void init() {
        List<PlaceModel> placeModelList = MedicalCenterController.getPlaceList();
        List<DepartmentModel> departmentModelList = MedicalCenterController.getDepartmentList();

        //如果本地没有数据，则加载服务器数据，如果有数据，后台自动加载
        if (CollectionUtils.isEmpty(placeModelList) || CollectionUtils.isEmpty(departmentModelList)) {
            showLoadingDialog(getString(com.markzhai.library.R.string.loading), false);
            MedicalCenterController.loadPlaceList(this);
            MedicalCenterController.loadDepartmentList(this);
        } else {
            MedicalCenterController.loadPlaceList(null);
            MedicalCenterController.loadDepartmentList(null);
            fillDepartment(departmentModelList);
            fillPlace(placeModelList);
        }

        departmentFilterButton.setText(MessageFormat.format(getString(R.string.department_filter), getString(R.string.no_filter)));
        placeFilterButton.setText(MessageFormat.format(getString(R.string.place_filter), getString(R.string.no_filter)));
    }

    @Override
    public void initTopbar(MZTopbar topbar) {
        super.initTopbar(topbar);
        topbar.setIcon(R.drawable.left_arrow, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getBaseActivity().onBackPressed();
            }
        });
        topbar.setTitle(R.string.disease_information);
    }

    @Override
    public void onLoadDepartmentSuccess(List<DepartmentModel> data) {
        hideLoadingDialog();
        fillDepartment(data);
    }

    @Override
    public void onLoadDepartmentError(String errorMessage) {
        hideLoadingDialog();
        showToast(errorMessage);
    }

    @Override
    public void onLoadPlaceSuccess(List<PlaceModel> data) {
        hideLoadingDialog();
        fillPlace(data);
    }

    @Override
    public void onLoadPlaceError(String errorMessage) {
        hideLoadingDialog();
        showToast(errorMessage);
    }

    private void fillDepartment(List<DepartmentModel> data) {
        for (final DepartmentModel model : data) {
            Button filterButton = getFilterButton();
            filterButton.setText(model.name);
            filterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    departmentFilterButton.setText(MessageFormat.format(getString(R.string.department_filter), model.name));
                }
            });

            departmentFlow.addView(filterButton);
        }
    }

    private void fillPlace(List<PlaceModel> data) {
        for (final PlaceModel model : data) {
            Button filterButton = getFilterButton();
            filterButton.setText(model.name);
            filterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    placeFilterButton.setText(MessageFormat.format(getString(R.string.place_filter), model.name));
                }
            });

            placeFlow.addView(filterButton);
        }
    }

    private Button getFilterButton() {
        Button btn = new Button(getBaseActivity());
        btn.setBackgroundResource(com.markzhai.library.R.drawable.selector_default_button);
        btn.setTextColor(getResources().getColor(com.markzhai.library.R.color.color_default_text));
        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        btn.setPadding(getResources().getDimensionPixelOffset(com.markzhai.library.R.dimen.margin_normal), getResources().getDimensionPixelOffset(com.markzhai.library.R.dimen.margin_small_more), getResources().getDimensionPixelOffset(com.markzhai.library.R.dimen.margin_normal), getResources().getDimensionPixelOffset(com.markzhai.library.R.dimen.margin_small_more));

        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 10;
        params.rightMargin = 10;
        params.topMargin = 10;
        params.bottomMargin = 10;

        btn.setLayoutParams(params);

        return btn;
    }
}

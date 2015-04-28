package com.markzhai.familydoctor.core.view.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.markzhai.familydoctor.R;
import com.markzhai.familydoctor.core.controller.HealthNewsController;
import com.markzhai.familydoctor.core.model.HealthNewsModel;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.framework.page.FragmentType;
import com.markzhai.library.utils.ImageUtils;
import com.markzhai.library.utils.StringUtils;
import com.markzhai.library.widget.MZTopbar;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/4/28.
 */
public class FragmentNewsDetail extends BaseFragment implements HealthNewsController.HealthNewsDetailCallback {

    private static final String DATA_KEY = "HEALTH_NEWS_MODEL";

    @InjectView(R.id.news_detail_title)
    private TextView titleView;

    @InjectView(R.id.news_detail_image)
    private ImageView newsImage;

    @InjectView(R.id.news_detail_date)
    private TextView dateView;

    @InjectView(R.id.news_detail_body)
    private TextView bodyView;

    public static FragmentRequest getRequest(HealthNewsModel data) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA_KEY, data);

        FragmentRequest request = new FragmentRequest(FragmentType.APP, FragmentNewsDetail.class, bundle);
        return request;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_news_detail;
    }

    @Override
    public void init() {
        Bundle data = getArguments();
        HealthNewsModel model = (HealthNewsModel) data.getSerializable(DATA_KEY);

        titleView.setText(model.title);
        dateView.setText(model.getDate());
        if (StringUtils.isEmpty(model.imgURL)) {
            newsImage.setVisibility(View.GONE);
        } else {
            newsImage.setVisibility(View.VISIBLE);
            ImageUtils.displayImage(HealthNewsModel.HOST + model.imgURL, newsImage);
        }

        HealthNewsController.loadDetails(model.id, this);
    }

    @Override
    public void initTopbar(MZTopbar topbar) {
        super.initTopbar(topbar);
        topbar.setTitle(R.string.health_news);
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

    @Override
    public void loadNewsDetailSuccess(String detail) {
        bodyView.setText(Html.fromHtml(detail));
    }

    @Override
    public void loadNewsDetailFailure(String errorMessage) {
        showToast(errorMessage);
    }
}

package com.markzhai.diablo3.core.view.fragment;

import android.view.View;

import com.markzhai.diablo3.R;
import com.markzhai.diablo3.core.controller.NewsController;
import com.markzhai.diablo3.core.model.NormalNews;
import com.markzhai.diablo3.core.model.SliderNews;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.utils.CollectionUtils;
import com.markzhai.library.widget.slider.Animations.DescriptionAnimation;
import com.markzhai.library.widget.slider.Indicators.PagerIndicator;
import com.markzhai.library.widget.slider.SliderLayout;
import com.markzhai.library.widget.slider.SliderTypes.BaseSliderView;
import com.markzhai.library.widget.slider.SliderTypes.TextSliderView;
import com.markzhai.library.widget.slider.Tricks.ViewPagerEx;

import java.util.List;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/5/20.
 */
public class FragmentHome extends BaseFragment implements NewsController.NewsLoadCallback {

    @InjectView(R.id.home_slider)
    private SliderLayout imageSlider;

    @InjectView(R.id.slider_indicator)
    private PagerIndicator sliderIndicator;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {

    }

    @Override
    public void initData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NewsController.loadNews(FragmentHome.this);
            }
        }, 100);
    }

    @Override
    public void loadSliderNewsSuccess(List<SliderNews> sliderNewses) {
        imageSlider.setVisibility(View.GONE);
        if (!CollectionUtils.isEmpty(sliderNewses)) {
            for (final SliderNews news : sliderNewses) {
                TextSliderView txtView = new TextSliderView(getBaseActivity());
                txtView.description(news.title).image(news.imageUrl).setScaleType(BaseSliderView.ScaleType.Fit).setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        showToast("Open URL : " + news.newsUrl);
                    }
                });
                imageSlider.addSlider(txtView);
            }

            imageSlider.setPresetTransformer(SliderLayout.Transformer.Default);
            imageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
            imageSlider.setDuration(5000);
            imageSlider.setCurrentPosition(0);
            imageSlider.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void loadNewsSuccess(List<NormalNews> normalNewses) {
        if (!CollectionUtils.isEmpty(normalNewses)) {
            showToast("Slider: " + normalNewses.get(0).title);
        }
    }

    @Override
    public void loadNewsFailure(String errorMessage) {
        showToast(errorMessage);
    }
}

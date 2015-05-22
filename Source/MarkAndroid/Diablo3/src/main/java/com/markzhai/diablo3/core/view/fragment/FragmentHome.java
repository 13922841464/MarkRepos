package com.markzhai.diablo3.core.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.markzhai.diablo3.R;
import com.markzhai.diablo3.core.controller.NewsController;
import com.markzhai.diablo3.core.model.NormalNews;
import com.markzhai.diablo3.core.model.SliderNews;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.utils.CollectionUtils;
import com.markzhai.library.utils.ImageUtils;
import com.markzhai.library.utils.UIUtils;
import com.markzhai.library.widget.MZTopbar;
import com.markzhai.library.widget.slider.Animations.DescriptionAnimation;
import com.markzhai.library.widget.slider.Indicators.PagerIndicator;
import com.markzhai.library.widget.slider.SliderLayout;
import com.markzhai.library.widget.slider.SliderTypes.BaseSliderView;
import com.markzhai.library.widget.slider.SliderTypes.TextSliderView;
import com.markzhai.library.widget.slider.Tricks.ViewPagerEx;

import java.util.ArrayList;
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

    @InjectView(R.id.home_list)
    private ListView homeList;
    private HomeListAdapter homeListAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {

    }

    @Override
    public void initTopbar(MZTopbar topbar) {
        super.initTopbar(topbar);
        topbar.setIcon(R.drawable.ic_launcher, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("icon click.");
            }
        });
    }

    @Override
    public void initData() {
        homeListAdapter = new HomeListAdapter();
        homeList.setAdapter(homeListAdapter);
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
            homeListAdapter.update(normalNewses);
        }
    }

    @Override
    public void loadNewsFailure(String errorMessage) {
        showToast(errorMessage);
    }

    class HomeListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        private List<NormalNews> normalNewses;

        public HomeListAdapter() {
            layoutInflater = layoutInflater.from(getBaseActivity());
            normalNewses = new ArrayList<NormalNews>();
        }

        public void update(List<NormalNews> data) {
            this.normalNewses.clear();
            this.normalNewses.addAll(data);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return normalNewses.size();
        }

        @Override
        public NormalNews getItem(int i) {
            return normalNewses.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            NormalNews item = getItem(i);

            ViewHolder holder = null;

            if (view == null) {
                holder = new ViewHolder();
                view = layoutInflater.inflate(R.layout.item_home_list, viewGroup, false);
                holder.titleView = (TextView) view.findViewById(R.id.home_item_title);
                holder.contentView = (TextView) view.findViewById(R.id.home_item_content);
                holder.dateView = (TextView) view.findViewById(R.id.home_item_date);
                holder.imageView = (ImageView) view.findViewById(R.id.home_item_image);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.imageView.setImageResource(R.drawable.loading);
            holder.titleView.setText(item.title);
            holder.contentView.setText(item.content);
            holder.dateView.setText(item.date);
            ImageUtils.displayImage(item.imageUrl, holder.imageView);

            return view;
        }
    }

    class ViewHolder {
        public TextView titleView;
        public TextView contentView;
        public ImageView imageView;
        public TextView dateView;
    }
}

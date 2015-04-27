package com.markzhai.familydoctor.core.view.fragment;

import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.markzhai.familydoctor.R;
import com.markzhai.familydoctor.core.controller.HealthNewsController;
import com.markzhai.familydoctor.core.model.HealthNewsModel;
import com.markzhai.library.framework.BaseApplication;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.utils.ImageUtils;
import com.markzhai.library.widget.MZTopbar;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/4/24.
 */
public class FragmentHome extends BaseFragment implements HealthNewsController.HealthNewsCallback, MZTopbar.DragListClickCallback {

    private static final int LOAD_LIMIT = 20;

    private static final List<MZTopbar.DragListTitle> CATEGORY_LIST = new ArrayList<MZTopbar.DragListTitle>();

    @InjectView(R.id.home_drawer)
    private DrawerLayout drawer;

    @InjectView(R.id.main_page_list)
    private PullToRefreshListView newsList;
    private NewsAdapter newsAdapter;

    private HealthNewsController.NewsType currentType = null;
    private int currentPage = 1;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        if (BaseApplication.isFirstRun()) {
            openDrawer();
        }
        getBaseActivity().setDrawer(drawer, Gravity.START);

        newsAdapter = new NewsAdapter();
        newsList.setMode(PullToRefreshBase.Mode.BOTH);
        newsList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage = 1;
                HealthNewsController.load(currentPage, LOAD_LIMIT, currentType, FragmentHome.this);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage++;
                HealthNewsController.load(currentPage, LOAD_LIMIT, currentType, FragmentHome.this);
            }
        });
        newsList.setAdapter(newsAdapter);
    }

    @Override
    public void initData() {
        showLoadingDialog(getString(R.string.loading), false);
        HealthNewsController.load(currentPage, LOAD_LIMIT, currentType, this);
    }

    @Override
    public void initTopbar(MZTopbar topbar) {
        super.initTopbar(topbar);
        topbar.setTitle(R.string.health_news);
        topbar.setIcon(R.drawable.topbar_menu, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleDrawer();
            }
        });

        CATEGORY_LIST.clear();
        CATEGORY_LIST.add(new CategoryMenu(getString(R.string.category_all)));
        CATEGORY_LIST.add(new CategoryMenu(HealthNewsController.NewsType.QIYEYAOWEN));
        CATEGORY_LIST.add(new CategoryMenu(HealthNewsController.NewsType.YILIAOXINWEN));
        CATEGORY_LIST.add(new CategoryMenu(HealthNewsController.NewsType.SHENGHUOTIESHI));
        CATEGORY_LIST.add(new CategoryMenu(HealthNewsController.NewsType.YAOPINXINWEN));
        CATEGORY_LIST.add(new CategoryMenu(HealthNewsController.NewsType.SHIPINXINWEN));
        CATEGORY_LIST.add(new CategoryMenu(HealthNewsController.NewsType.SHEHUIREDIAN));
        CATEGORY_LIST.add(new CategoryMenu(HealthNewsController.NewsType.JIBINGKUAIXUN));
        topbar.setMenuList(R.drawable.category_list, CATEGORY_LIST, this);
    }

    private void toggleDrawer() {
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START);
        } else {
            drawer.openDrawer(Gravity.START);
        }
    }

    private void openDrawer() {
        drawer.openDrawer(Gravity.START);
    }

    private void closeDrawer() {
        drawer.closeDrawer(Gravity.START);
    }

    @Override
    public void loadNewsSuccess(List<HealthNewsModel> result) {
        newsList.onRefreshComplete();
        hideLoadingDialog();
        newsAdapter.update(result);
    }

    @Override
    public void loadNewsFailure(String errorMessage) {
        newsList.onRefreshComplete();
        hideLoadingDialog();
        showToast(errorMessage);
    }

    @Override
    public void itemClicked(int position, Object item) {
        topbar.setTitle(((CategoryMenu) item).getTitle());
        currentType = ((CategoryMenu) item).getNewsType();
        currentPage = 1;
        showLoadingDialog(getString(R.string.loading), false);
        HealthNewsController.load(currentPage, LOAD_LIMIT, currentType, this);
    }

    class NewsAdapter extends BaseAdapter {

        private List<HealthNewsModel> data;

        private LayoutInflater layoutInflater;

        public NewsAdapter() {
            layoutInflater = LayoutInflater.from(getBaseActivity());
            if (data == null) {
                data = new ArrayList<HealthNewsModel>();
            }
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public HealthNewsModel getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final HealthNewsModel item = getItem(i);
            ViewHolder holder = null;
            if (view == null) {
                view = layoutInflater.inflate(R.layout.item_health_news, viewGroup, false);
                holder = new ViewHolder();

                holder.newsImg = (ImageView) view.findViewById(R.id.news_img);
                holder.newsTitle = (TextView) view.findViewById(R.id.news_title);
                holder.newsDate = (TextView) view.findViewById(R.id.news_date);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.newsImg.setImageResource(R.drawable.loading);
            holder.newsTitle.setText(item.title);
            holder.newsDate.setText(item.getDate());
            ImageUtils.displayImage(item.imgURL, holder.newsImg);

            return view;
        }

        private void update(List<HealthNewsModel> result) {
            if (currentPage == 1) {
                this.data.clear();
            }
            this.data.addAll(result);
            notifyDataSetChanged();
        }
    }

    class ViewHolder {
        public ImageView newsImg;
        public TextView newsTitle;
        public TextView newsDate;
    }

    class CategoryMenu implements MZTopbar.DragListTitle {

        private HealthNewsController.NewsType newsType;

        private String title;

        public CategoryMenu(String title) {
            this.title = title;
        }

        public CategoryMenu(HealthNewsController.NewsType type) {
            this.newsType = type;
        }

        public HealthNewsController.NewsType getNewsType() {
            return newsType;
        }

        @Override
        public String getTitle() {
            return newsType != null ? newsType.getTypeName(getBaseActivity()) : title;
        }

        @Override
        public int getIcon() {
            return R.drawable.item_icon;
        }
    }
}

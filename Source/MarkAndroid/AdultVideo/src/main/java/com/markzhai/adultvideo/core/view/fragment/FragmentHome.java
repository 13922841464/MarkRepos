package com.markzhai.adultvideo.core.view.fragment;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.markzhai.adultvideo.R;
import com.markzhai.adultvideo.core.controller.EmpflixController;
import com.markzhai.adultvideo.core.model.empflix.EmpflixCategory;
import com.markzhai.adultvideo.core.model.empflix.EmpflixDB;
import com.markzhai.adultvideo.core.model.empflix.EmpflixVideoModel;
import com.markzhai.adultvideo.core.view.VideoActivity;
import com.markzhai.adultvideo.core.view.dialog.PickCategoryDialog;
import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.utils.ImageUtils;
import com.markzhai.library.widget.MZTopbar;

import java.util.ArrayList;
import java.util.List;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/3/26.
 */
public class FragmentHome extends BaseFragment implements EmpflixController.LoadPageDataCallback, EmpflixController.LoadCategoryCallback, PickCategoryDialog.CategorySelectedCallback, MZTopbar.DragListClickCallback {

    @InjectView(R.id.home_video_list)
    private PullToRefreshListView homeVideoListView;
    private HomeVideoListAdapter homeVideoListAdapter;

    private int currentPage = 1;

    @InjectView(R.id.adView)
    private AdView adview;
    private AdRequest adRequest;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initTopbar(MZTopbar topbar) {
        super.initTopbar(topbar);

        topbar.setTitle(R.string.top);

        topbar.setIcon(R.drawable.ic_launcher, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("icon");
            }
        });

        List<MZTopbar.DragListTitle> menuItems = new ArrayList<MZTopbar.DragListTitle>();
        menuItems.add(new MZTopbar.DragListTitle() {
            @Override
            public String getTitle() {
                return getString(R.string.category);
            }

            @Override
            public int getIcon() {
                return R.drawable.filter;
            }
        });
//        menuItems.add(new MZTopbar.DragListTitle() {
//            @Override
//            public String getTitle() {
//                return getString(R.string.feedback);
//            }
//
//            @Override
//            public int getIcon() {
//                return R.drawable.feedback;
//            }
//        });
        topbar.setMenuList(R.drawable.more, menuItems, this);
    }

    @Override
    public void init() {
        initAD();

        homeVideoListAdapter = new HomeVideoListAdapter();
        homeVideoListView.setAdapter(homeVideoListAdapter);
        homeVideoListView.setMode(PullToRefreshBase.Mode.BOTH);
        homeVideoListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage = 1;
                EmpflixController.loadPageData(FragmentHome.this);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage++;
                EmpflixController.loadPageData(FragmentHome.this, currentPage);
            }
        });

        showLoadingDialog(getString(R.string.loading), false);
        EmpflixController.loadPageData(FragmentHome.this);
    }

    private void initAD() {
        adRequest = new AdRequest.Builder().build();

        loadAD();
    }

    private void loadAD() {
        adview.loadAd(adRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
        adview.resume();
        initAD();
    }

    @Override
    public void onPause() {
        super.onPause();
        adview.pause();
    }

    @Override
    public void onLoadPageDataSuccess(boolean homePage, List<EmpflixVideoModel> result) {
        hideLoadingDialog();
        homeVideoListView.onRefreshComplete();

        if (homePage) {
            homeVideoListAdapter.update(result);
        } else {
            homeVideoListAdapter.addPage(result);
        }
    }

    @Override
    public void onLoadPageDataFailure(Throwable throwable) {
        hideLoadingDialog();
        showToast("Load Failure!!!\n" + throwable.getMessage());
    }

    @Override
    public void onSelected(EmpflixCategory category) {
        topbar.setTitle(category.getNameRes());
        showLoadingDialog(getString(R.string.loading), false);
        EmpflixController.loadCategory(category, FragmentHome.this);
    }

    @Override
    public void onLoadCategorySuccess(boolean homePage, List<EmpflixVideoModel> result) {
        hideLoadingDialog();
        homeVideoListView.onRefreshComplete();

        if (homePage) {
            homeVideoListAdapter.update(result);
        } else {
            homeVideoListAdapter.addPage(result);
        }
    }

    @Override
    public void onLoadCategoryFailure(Throwable throwable) {
        hideLoadingDialog();
        showToast("Load Failure!!!\n" + throwable.getMessage());
    }

    private void openVideoPlayer(EmpflixVideoModel item) {
        Intent videoIntent = new Intent(getBaseActivity(), VideoActivity.class);
        videoIntent.putExtra(FragmentVideo.VIDEO_INFO, item);
        startActivity(videoIntent);
    }

    @Override
    public void itemClicked(int position, Object item) {
        switch (position) {
            case 0:
                PickCategoryDialog dialog = new PickCategoryDialog(getBaseActivity());
                dialog.setCategoryCallback(FragmentHome.this);
                dialog.show();
                break;
            case 1:
                break;
        }
    }

    class HomeVideoListAdapter extends BaseAdapter {

        private List<EmpflixVideoModel> videoList = new ArrayList<EmpflixVideoModel>();

        public HomeVideoListAdapter() {
            List<EmpflixVideoModel> dbList = EmpflixDB.getAll(getBaseActivity());
            if (dbList != null) {
                videoList = dbList;
            }
        }

        public void update(List<EmpflixVideoModel> videoList) {
            this.videoList.clear();
            this.videoList = videoList;
            notifyDataSetChanged();
        }

        public void addPage(List<EmpflixVideoModel> videoList) {
            if (videoList != null && videoList.size() > 0) {
                this.videoList.addAll(videoList);
                notifyDataSetChanged();
            }
        }

        @Override
        public int getCount() {
            return videoList.size();
        }

        @Override
        public EmpflixVideoModel getItem(int position) {
            return videoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final EmpflixVideoModel item = getItem(position);

            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getBaseActivity()).inflate(R.layout.item_home_video, parent, false);
                holder = new ViewHolder();
                holder.videoImageView = (ImageView) convertView.findViewById(R.id.video_image);
                holder.videoNameView = (TextView) convertView.findViewById(R.id.video_name);
                holder.videoDurationView = (TextView) convertView.findViewById(R.id.video_duration);
                holder.downloadButton = (Button) convertView.findViewById(R.id.download_btn);
                holder.playButton = (ImageView) convertView.findViewById(R.id.video_play);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.videoImageView.setImageResource(R.drawable.loading_empty);
            ImageUtils.displayImage(item.videoThumb, holder.videoImageView);
            holder.videoNameView.setText(item.videoTitle);
            holder.videoDurationView.setText(item.videoDuration);
            holder.downloadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mimutypeMap = MimeTypeMap.getFileExtensionFromUrl(item.videoURL);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(mimutypeMap);
                    intent.setDataAndType(Uri.parse(item.videoURL), mimeType);
                    startActivity(intent);
                }
            });
            holder.playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openVideoPlayer(item);
                }
            });

            return convertView;
        }
    }

    class ViewHolder {
        public ImageView videoImageView;
        public TextView videoNameView;
        public TextView videoDurationView;
        public Button downloadButton;
        public ImageView playButton;
    }
}

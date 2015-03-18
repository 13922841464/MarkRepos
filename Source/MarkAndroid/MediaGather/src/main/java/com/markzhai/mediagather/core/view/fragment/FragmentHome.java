package com.markzhai.mediagather.core.view.fragment;

import android.view.View;
import android.widget.RelativeLayout;

import com.markzhai.library.framework.BaseFragment;
import com.markzhai.library.utils.DBUtils;
import com.markzhai.mediagather.R;
import com.markzhai.mediagather.core.model.sys.Configuration;

import roboguice.inject.InjectView;

/**
 * Created by marktlzhai on 2015/3/17.
 */
public class FragmentHome extends BaseFragment implements View.OnClickListener {

    @InjectView(R.id.category_movie)
    private RelativeLayout movieView;

    @InjectView(R.id.category_picture)
    private RelativeLayout pictureView;

    @InjectView(R.id.category_music)
    private RelativeLayout musicView;

    @InjectView(R.id.category_novel)
    private RelativeLayout novelView;

    @InjectView(R.id.category_news)
    private RelativeLayout newsView;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        movieView.setOnClickListener(this);
        pictureView.setOnClickListener(this);
        musicView.setOnClickListener(this);
        novelView.setOnClickListener(this);
        newsView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int clickID = v.getId();

        switch (clickID) {
            case R.id.category_movie:
                toMovie();
                break;
            case R.id.category_picture:
                toPicture();
                break;
            case R.id.category_music:
                toMusic();
                break;
            case R.id.category_news:
                toNews();
                break;
            case R.id.category_novel:
                toNovel();
                break;
        }
    }

    private void toMovie() {

    }

    private void toPicture() {

    }

    private void toMusic() {

    }

    private void toNews() {

    }

    private void toNovel() {

    }
}

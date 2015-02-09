package com.markzhai.library.framework;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.loopj.android.http.AsyncHttpClient;
import com.markzhai.library.R;
import com.markzhai.library.framework.page.DefaultFragmentManager;
import com.markzhai.library.framework.page.FragmentRequest;
import com.tencent.tauth.Tencent;

import roboguice.activity.RoboFragmentActivity;

/**
 * Created by marktlzhai on 2015/1/24.
 */
public abstract class BaseActivity extends RoboFragmentActivity {

    protected abstract FragmentRequest installHome();

    public Typeface installFont() {
        return null;
    }

    /**
     * Frame管理
     */
    private DefaultFragmentManager defaultFragmentManager;

    /**
     * 是否为全屏
     */
    protected boolean fullscreen;

    /**
     * Async-http-client
     */
    protected AsyncHttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        httpClient = new AsyncHttpClient();
        defaultFragmentManager = new DefaultFragmentManager(this);
        setContentView(R.layout.activity_main);
        startFragment(installHome());
    }

    /**
     * 开启一个Frame
     */
    public void startFragment(FragmentRequest request) {
        // 设置是否全屏
        if (!fullscreen == request.isFullScreen()) {
            setFullscreenn(request.isFullScreen());
        }

        defaultFragmentManager.insertFragmentToActivity(request);
    }

    /**
     * 设置全屏
     */
    public void setFullscreenn(boolean fullscreen) {
        if (this.fullscreen == fullscreen) {
            return;
        }
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        if (fullscreen) {
            params.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        } else {
            params.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        window.setAttributes(params);
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        this.fullscreen = fullscreen;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(BaseApplication.tencent != null) {
            BaseApplication.tencent.onActivityResult(requestCode, resultCode, data);
        }
    }
}

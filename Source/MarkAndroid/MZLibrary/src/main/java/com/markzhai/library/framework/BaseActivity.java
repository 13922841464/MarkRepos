package com.markzhai.library.framework;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.markzhai.library.R;
import com.markzhai.library.framework.page.DefaultFragmentManager;
import com.markzhai.library.framework.page.FragmentRequest;

import roboguice.activity.RoboFragmentActivity;

/**
 * Created by marktlzhai on 2015/1/24.
 */
public abstract class BaseActivity extends RoboFragmentActivity {

    protected abstract FragmentRequest installHome();

    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    private long waitTime = 2000;
    private long touchTime = 0;

    public Typeface installFont() {
        return null;
    }

    /**
     * Frame管理
     */
    protected static DefaultFragmentManager defaultFragmentManager;

    /**
     * 是否为全屏
     */
    protected boolean fullscreen;

    /**
     * Async-http-client
     */
    protected AsyncHttpClient httpClient;

    private DrawerLayout drawer;
    private int drawerGravity = Gravity.START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseApplication.setBaseActivity(this);

        init();
    }

    public void setDrawer(DrawerLayout drawer, int drawerGravity) {
        this.drawer = drawer;
        this.drawerGravity = drawerGravity;
    }

    /**
     * 初始化
     */
    private void init() {
        httpClient = new AsyncHttpClient();
        defaultFragmentManager = new DefaultFragmentManager(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(getLayoutID());
        startFragment(R.id.fragment_container, installHome());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 开启一个Frame
     */
    public void startFragment(int layoutID, FragmentRequest request) {
        // 设置是否全屏
        if (!fullscreen == request.isFullScreen()) {
            setFullscreenn(request.isFullScreen());
        }

        defaultFragmentManager.insertFragmentToActivity(layoutID, request);
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

    public static BaseFragment getCurrentFragment() {
        return defaultFragmentManager.getCurrentFragment();
    }

    public void back() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(drawerGravity)) {
            drawer.closeDrawer(drawerGravity);
            return;
        }

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            super.onBackPressed();
        } else {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                Toast.makeText(this, R.string.exit_press_again, Toast.LENGTH_SHORT).show();
                touchTime = currentTime;
            } else {
                finish();
            }
        }
    }
}

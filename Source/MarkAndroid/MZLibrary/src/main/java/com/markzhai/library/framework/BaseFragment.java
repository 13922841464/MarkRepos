package com.markzhai.library.framework;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.utils.UIUtils;

import roboguice.fragment.RoboFragment;

/**
 * Created by marktlzhai on 2015/1/24.
 */
public abstract class BaseFragment extends RoboFragment {
    public abstract int getLayoutResId();

    public abstract void init();

    public Typeface installFont() {
        return null;
    }

    private BaseActivity baseActivity;

    private View rootView;

    protected static final Handler handler = new Handler();

    /**
     * Async-http-client
     */
    protected AsyncHttpClient httpClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // 处理异步线程onPreExecute 不执行的问题
        try {
            Class.forName("android.os.AsyncTask");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        rootView = inflater.inflate(getLayoutResId(), container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        httpClient = new AsyncHttpClient();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.baseActivity = (BaseActivity) getActivity();
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        //设置默认字体
        Typeface defaultTypeface = getBaseActivity().installFont();
        Typeface typeface = installFont();
        if (defaultTypeface != null) {
            if (rootView instanceof ViewGroup) {
                UIUtils.setTypeFace((ViewGroup) rootView, typeface != null ? typeface : defaultTypeface);
            } else if (rootView instanceof TextView) {
                UIUtils.setTypeFace((TextView) rootView, typeface != null ? typeface : defaultTypeface);
            }
        }
    }

    public BaseActivity getBaseActivity() {
        return this.baseActivity;
    }

    public void showToast(String message) {
        showShortToast(message);
    }

    public void showShortToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void showShortToast(int messageResID) {
        Toast.makeText(getActivity(), messageResID, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    public void showLongToast(int messageResID) {
        Toast.makeText(getActivity(), messageResID, Toast.LENGTH_LONG).show();
    }

    public String getFragmentTag() {
        return getClass().getName();
    }

    public void startFragment(FragmentRequest request) {
        baseActivity.startFragment(request);
    }

    public boolean isEquals(Object o) {
        if (o instanceof BaseFragment) {
            return ((BaseFragment) o).getFragmentTag().equals(getFragmentTag());
        } else {
            return false;
        }
    }
}

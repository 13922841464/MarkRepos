package com.markzhai.library.framework;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.markzhai.library.R;
import com.markzhai.library.framework.dialog.LoadingDialog;
import com.markzhai.library.framework.page.FragmentRequest;
import com.markzhai.library.utils.UIUtils;
import com.markzhai.library.widget.MZTopbar;

import roboguice.fragment.RoboFragment;

/**
 * Created by marktlzhai on 2015/1/24.
 */
public abstract class BaseFragment extends RoboFragment {
    public abstract int getLayoutResId();

    public abstract void init();

    public void initTopbar(MZTopbar topbar) {

    }

    public void showToUser() {

    }

    public Typeface installFont() {
        return null;
    }

    private BaseActivity baseActivity;

    protected View rootView;

    protected static final Handler handler = new Handler();

    protected MZTopbar topbar;

    private Dialog loadingDialog;

    /**
     * Async-http-client
     */
    protected AsyncHttpClient httpClient;

    protected static final Handler inHandler = new Handler();

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

        topbar = (MZTopbar) rootView.findViewById(R.id.topbar);
        if (topbar != null) {
            initTopbar(topbar);
        }
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

    protected View findViewById(int viewID) {
        return rootView.findViewById(viewID);
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

    public void startFragment(Class<? extends BaseFragment> fragmentClass) {
        startFragment(new FragmentRequest(fragmentClass));
    }

    public void startFragment(FragmentRequest request) {
        startFragment(R.id.fragment_container, request);
    }

    public void startFragment(int layoutID, FragmentRequest request) {
        baseActivity.startFragment(layoutID, request);
    }

    public boolean isEquals(Object o) {
        if (o instanceof BaseFragment) {
            return ((BaseFragment) o).getFragmentTag().equals(getFragmentTag());
        } else {
            return false;
        }
    }

    public void showLoadingDialog(String message, boolean cancelable) {
        loadingDialog = LoadingDialog.showLoadingDialog(getBaseActivity(), message, cancelable);
    }

    public void hideLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            showToUser();
        }
    }
}

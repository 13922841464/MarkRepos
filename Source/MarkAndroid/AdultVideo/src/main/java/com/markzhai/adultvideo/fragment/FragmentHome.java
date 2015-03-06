package com.markzhai.adultvideo.fragment;

import com.loopj.android.http.TextHttpResponseHandler;
import com.markzhai.adultvideo.R;
import com.markzhai.library.framework.BaseFragment;

import org.apache.http.Header;

/**
 * Created by marktlzhai on 2015/2/28.
 */
public class FragmentHome extends BaseFragment {

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void init() {
        httpClient.post("http://m.empflix.com", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                showToast("failure[" + responseString + "]");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                showToast("success\n" + responseString);
            }
        });
    }
}

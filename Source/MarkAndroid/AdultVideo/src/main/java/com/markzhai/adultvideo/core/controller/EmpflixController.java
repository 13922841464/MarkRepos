package com.markzhai.adultvideo.core.controller;

import android.content.Context;

import com.loopj.android.http.TextHttpResponseHandler;
import com.markzhai.adultvideo.core.App;
import com.markzhai.adultvideo.core.model.empflix.EmpflixDB;
import com.markzhai.adultvideo.core.model.empflix.EmpflixVideoModel;
import com.markzhai.library.framework.core.controller.MZController;

import org.apache.http.Header;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by marktlzhai on 2015/3/23.
 */
public class EmpflixController extends MZController {

    public static interface LoadPageDataCallback {
        void onLoadPageDataSuccess(boolean homePage, List<EmpflixVideoModel> result);

        void onLoadPageDataFailure(Throwable throwable);
    }

    private static final String PAGE_URL = "http://m.empflix.com/featured/{0}";

    /**
     * 加载第一页
     *
     * @param callback
     */
    public static void loadPageData(final LoadPageDataCallback callback) {
        if (callback != null) {
            asyncHttpClient.post(MessageFormat.format(PAGE_URL, 1), new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    callback.onLoadPageDataFailure(throwable);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    List<EmpflixVideoModel> result = EmpflixVideoModel.parseList(responseString);
                    EmpflixDB.resetDB(App.getApplication());
                    EmpflixDB.insert(App.getApplication(), result);
                    callback.onLoadPageDataSuccess(true, result);
                }
            });
        }
    }

    /**
     * 加载指定页数
     *
     * @param callback
     * @param page
     */
    public static void loadPageData(final LoadPageDataCallback callback, final int page) {
        if (callback != null) {
            asyncHttpClient.post(MessageFormat.format(PAGE_URL, page), new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    callback.onLoadPageDataFailure(throwable);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    List<EmpflixVideoModel> result = EmpflixVideoModel.parseList(responseString);
                    EmpflixDB.insert(App.getApplication(), result);
                    callback.onLoadPageDataSuccess(false, result);
                }
            });
        }
    }
}

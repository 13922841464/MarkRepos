package com.markzhai.familydoctor.core.controller;

import android.content.Context;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.markzhai.familydoctor.R;
import com.markzhai.familydoctor.core.model.HealthNewsModel;
import com.markzhai.library.framework.BaseApplication;
import com.markzhai.library.framework.core.controller.MZController;
import com.markzhai.talkingdata.TalkingData;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marktlzhai on 2015/4/27.
 */
public class HealthNewsController extends MZController {

    public static enum NewsType {
        //企业要闻
        QIYEYAOWEN(1, R.string.category_1),
        //医疗新闻
        YILIAOXINWEN(2, R.string.category_2),
        //生活贴士
        SHENGHUOTIESHI(3, R.string.category_3),
        //药品新闻
        YAOPINXINWEN(4, R.string.category_4),
        //食品新闻
        SHIPINXINWEN(5, R.string.category_5),
        //社会热点
        SHEHUIREDIAN(6, R.string.category_6),
        //疾病快讯
        JIBINGKUAIXUN(7, R.string.category_7);

        private int typeID = 1;
        private int typeName = 0;

        private NewsType(int typeID, int typeNameRes) {
            this.typeID = typeID;
            this.typeName = typeNameRes;
        }

        public int getTypeID() {
            return typeID;
        }

        public String getTypeName(Context context) {
            return context.getString(typeName);
        }
    }

    private static final String ERROR_NET = "服务器连接超时，请稍后再试。";

    public static final String URL_HEALTH_NEWS_LIST = "http://api.yi18.net/news/list";

    public static interface HealthNewsCallback {
        void loadNewsSuccess(List<HealthNewsModel> result);

        void loadNewsFailure(String errorMessage);
    }

    public static void load(int page, int limit, NewsType type, final HealthNewsCallback callback) {
        RequestParams params = new RequestParams();
        params.add("page", String.valueOf(page));
        params.add("limit", String.valueOf(limit));
        if (type != null) {
            params.add("id", String.valueOf(type.getTypeID()));
        }
        asyncHttpClient.get(URL_HEALTH_NEWS_LIST, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                boolean success = response.optBoolean("success", false);
                if (success) {
                    JSONArray resultArr = response.optJSONArray("yi18");
                    if (resultArr != null && resultArr.length() > 0) {
                        List<HealthNewsModel> result = new ArrayList<HealthNewsModel>();
                        for (int i = 0; i < resultArr.length(); i++) {
                            result.add(new HealthNewsModel(resultArr.optJSONObject(i)));
                        }
                        if (callback != null) {
                            callback.loadNewsSuccess(result);
                        }
                    }
                } else {
                    TalkingData.onError(BaseApplication.getApplication(), new Exception(ERROR_NET));
                    if (callback != null) {
                        callback.loadNewsFailure(ERROR_NET);
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                TalkingData.onError(BaseApplication.getApplication(), throwable);
                if (callback != null) {
                    callback.loadNewsFailure(ERROR_NET);
                }
            }
        });
    }
}

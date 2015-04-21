package com.markzhai.healthkeeper.core.controller.medicalcenter;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.markzhai.healthkeeper.core.model.medicalcenter.DepartmentModel;
import com.markzhai.healthkeeper.core.model.medicalcenter.PlaceModel;
import com.markzhai.library.framework.BaseApplication;
import com.markzhai.library.framework.core.controller.MZController;
import com.markzhai.library.utils.CollectionUtils;
import com.markzhai.library.utils.SPUtils;
import com.markzhai.talkingdata.TalkingData;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by marktlzhai on 2015/4/21.
 */
public class MedicalCenterController extends MZController {

    public static interface LoadPlaceCallback {
        void onLoadPlaceSuccess(List<PlaceModel> data);

        void onLoadPlaceError(String errorMessage);
    }

    public static interface LoadDepartmentCallback {
        void onLoadDepartmentSuccess(List<DepartmentModel> data);

        void onLoadDepartmentError(String errorMessage);
    }

    private static final String SP_DEPARTMENT_LIST = "DEPARTMENT_LIST";
    private static final String URL_LOAD_DEPARTMENT = "http://api.yi18.net/disease/place";

    private static final String SP_PLACE_LIST = "PLACE_LIST";
    private static final String URL_LOAD_PLACE = "http://api.yi18.net/disease/department";

    public static void loadPlaceList(final LoadPlaceCallback callback) {
        asyncHttpClient.post(URL_LOAD_PLACE, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                if (response.optBoolean("success")) {
                    List<PlaceModel> result = new ArrayList<PlaceModel>();
                    JSONArray placeArr = response.optJSONArray("yi18");
                    for (int i = 0; i < placeArr.length(); i++) {
                        JSONObject placeObj = placeArr.optJSONObject(i);
                        result.add(new PlaceModel(placeObj.optInt("id"), placeObj.optString("name")));
                    }
                    savePlaceList(result);
                    if (callback != null) {
                        callback.onLoadPlaceSuccess(result);
                    }
                } else {
                    if (callback != null) {
                        callback.onLoadPlaceError(JSON_PARSE_EXCEPTION.getMessage());
                    }
                    TalkingData.onError(BaseApplication.getApplication(), JSON_PARSE_EXCEPTION);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                if (callback != null) {
                    callback.onLoadPlaceError(responseString);
                }
                TalkingData.onError(BaseApplication.getApplication(), throwable);
            }
        });
    }

    public static void savePlaceList(List<PlaceModel> data) {
        Set<String> spData = new HashSet<String>();
        if (!CollectionUtils.isEmpty(data)) {
            for (PlaceModel model : data) {
                spData.add(model.toString());
            }
        }
        SPUtils.putStringSet(SP_PLACE_LIST, spData);
    }

    public static List<PlaceModel> getPlaceList() {
        Set<String> placeSet = SPUtils.getStringSet(SP_PLACE_LIST, null);
        if (placeSet == null) {
            return null;
        } else {
            List<PlaceModel> result = new ArrayList<PlaceModel>();

            for (String placeStr : placeSet) {
                result.add(new PlaceModel(placeStr));
            }

            return result;
        }
    }

    public static void loadDepartmentList(final LoadDepartmentCallback callback) {
        asyncHttpClient.post(URL_LOAD_DEPARTMENT, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                if (response.optBoolean("success")) {
                    List<DepartmentModel> result = new ArrayList<DepartmentModel>();
                    JSONArray placeArr = response.optJSONArray("yi18");
                    for (int i = 0; i < placeArr.length(); i++) {
                        JSONObject placeObj = placeArr.optJSONObject(i);
                        result.add(new DepartmentModel(placeObj.optInt("id"), placeObj.optString("name")));
                    }
                    saveDepartmentList(result);
                    if (callback != null) {
                        callback.onLoadDepartmentSuccess(result);
                    }
                } else {
                    if (callback != null) {
                        callback.onLoadDepartmentError(JSON_PARSE_EXCEPTION.getMessage());
                    }
                    TalkingData.onError(BaseApplication.getApplication(), JSON_PARSE_EXCEPTION);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                if (callback != null) {
                    callback.onLoadDepartmentError(responseString);
                }
                TalkingData.onError(BaseApplication.getApplication(), throwable);
            }
        });
    }

    public static void saveDepartmentList(List<DepartmentModel> data) {
        Set<String> spData = new HashSet<String>();
        if (!CollectionUtils.isEmpty(data)) {
            for (DepartmentModel model : data) {
                spData.add(model.toString());
            }
        }
        SPUtils.putStringSet(SP_DEPARTMENT_LIST, spData);
    }

    public static List<DepartmentModel> getDepartmentList() {
        Set<String> placeSet = SPUtils.getStringSet(SP_DEPARTMENT_LIST, null);
        if (placeSet == null) {
            return null;
        } else {
            List<DepartmentModel> result = new ArrayList<DepartmentModel>();

            for (String placeStr : placeSet) {
                result.add(new DepartmentModel(placeStr));
            }

            return result;
        }
    }
}

package com.markzhai.familydoctor.core.model;

import com.markzhai.library.framework.BaseApplication;
import com.markzhai.library.framework.core.model.MZModel;
import com.markzhai.talkingdata.TalkingData;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by marktlzhai on 2015/4/27.
 */
public class HealthNewsModel extends MZModel {

    private static final String HOST = "http://www.yi18.net/";

    public int id = 0;
    public String title = "";
    public String tag = "";
    public String imgURL = "";
    public int clickCount = 0;
    public String date = "";

    public HealthNewsModel() {

    }

    public HealthNewsModel(JSONObject jsonOBJ) {
        if (jsonOBJ != null) {
            id = jsonOBJ.optInt("id", 0);
            title = jsonOBJ.optString("title", "");
            tag = jsonOBJ.optString("tag", "");
            imgURL = HOST + jsonOBJ.optString("img", "");
            clickCount = jsonOBJ.optInt("count", 0);
            date = jsonOBJ.optString("time", "");
        }
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a", Locale.US);
        try {
            return DateFormatUtils.SMTP_DATETIME_FORMAT.format(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }
}

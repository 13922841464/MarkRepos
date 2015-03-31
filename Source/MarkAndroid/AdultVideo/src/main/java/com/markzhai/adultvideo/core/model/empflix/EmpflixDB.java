package com.markzhai.adultvideo.core.model.empflix;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marktlzhai on 2015/3/31.
 */
public class EmpflixDB {

    public synchronized static void resetDB(Context context) {
        Uri uri = EmpflixColumns.CONTENT_URI;
        context.getContentResolver().delete(uri, null, null);
    }

    public synchronized static void insert(Context context, EmpflixVideoModel model) {
        ContentValues values = new ContentValues();
        values.clear();

        values.put(EmpflixColumns.videoURL, model.videoURL);
        values.put(EmpflixColumns.videoTitle, model.videoTitle);
        values.put(EmpflixColumns.videoThumb, model.videoThumb);
        values.put(EmpflixColumns.videoDuration, model.videoDuration);

        context.getContentResolver().insert(EmpflixColumns.CONTENT_URI, values);
    }

    public synchronized static void insert(Context context, List<EmpflixVideoModel> models) {
        ContentValues[] values = new ContentValues[models.size()];

        int count = models.size();
        EmpflixVideoModel model = null;
        for (int i = 0; i < count; i++) {
            model = models.get(i);
            values[i] = new ContentValues();
            values[i].clear();

            values[i].put(EmpflixColumns.videoURL, model.videoURL);
            values[i].put(EmpflixColumns.videoTitle, model.videoTitle);
            values[i].put(EmpflixColumns.videoThumb, model.videoThumb);
            values[i].put(EmpflixColumns.videoDuration, model.videoDuration);
        }

        context.getContentResolver().bulkInsert(EmpflixColumns.CONTENT_URI, values);
    }

    public synchronized static List<EmpflixVideoModel> getAll(Context context) {
        List<EmpflixVideoModel> result = new ArrayList<EmpflixVideoModel>();
        EmpflixVideoModel model;

        Uri uri = Uri.withAppendedPath(EmpflixColumns.CONTENT_URI, "");//
        String[] projection = {EmpflixColumns.videoURL, EmpflixColumns.videoTitle, EmpflixColumns.videoThumb, EmpflixColumns.videoDuration};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) {
            return null;
        }
        if (!cursor.moveToFirst()) {
            return null;
        }
        do {
            model = new EmpflixVideoModel();
            model.videoURL = cursor.getString(cursor.getColumnIndex(EmpflixColumns.videoURL));
            model.videoTitle = cursor.getString(cursor.getColumnIndex(EmpflixColumns.videoTitle));
            model.videoThumb = cursor.getString(cursor.getColumnIndex(EmpflixColumns.videoThumb));
            model.videoDuration = cursor.getString(cursor.getColumnIndex(EmpflixColumns.videoDuration));
            result.add(model);
        } while (cursor.moveToNext());

        cursor.close();

        return result;
    }
}

package com.markzhai.adultvideo.core.model.empflix;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marktlzhai on 2015/3/31.
 */
public class EmpflixDBHelper extends SQLiteOpenHelper {

    private static final String APP_DB_NAME = "adultvideo.db";
    private static final int APP_DB_VERSION = 1;

    public static final String EMPFLIX_TABLE_NAME = "empflix";

    private Context context;

    public EmpflixDBHelper(Context context) {
        super(context, APP_DB_NAME, null, APP_DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(getCreateTableString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion <= oldVersion) {
            return;
        }
        db.execSQL("DROP TABLE IF EXISTS " + EMPFLIX_TABLE_NAME);

        onCreate(db);
    }

    private String getCreateTableString() {
        String sqlString = "CREATE TABLE " + EMPFLIX_TABLE_NAME + "(" + EmpflixColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + EmpflixColumns.videoURL + " TEXT," + EmpflixColumns.videoTitle + " TEXT," + EmpflixColumns.videoDuration + " TEXT," + EmpflixColumns.videoThumb
                + " TEXT" + ") ";

        return sqlString;
    }
}

package com.markzhai.adultvideo.core.model.empflix;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by marktlzhai on 2015/3/31.
 */
public class EmpflixContentProvider extends ContentProvider {

    private EmpflixDBHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new EmpflixDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(EmpflixDBHelper.EMPFLIX_TABLE_NAME);
        Cursor cur = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);

        getContext().getContentResolver().notifyChange(uri, null);
        return cur;
    }

    @Override
    public String getType(Uri uri) {
        return EmpflixColumns.CONTENT_TYPE;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int numValues = 0;
        db.beginTransaction();
        try {
            numValues = values.length;
            for (int i = 0; i < numValues; i++) {
                insert(uri, values[i]);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return numValues;

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (null == values)
            return null;

        ContentValues insertValues = new ContentValues(values);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long id = db.insert(EmpflixDBHelper.EMPFLIX_TABLE_NAME, null, insertValues);
        if (id > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;
        } else {
            return null;
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.delete(EmpflixDBHelper.EMPFLIX_TABLE_NAME, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.update(EmpflixDBHelper.EMPFLIX_TABLE_NAME, values, selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}

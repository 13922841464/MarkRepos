package com.markzhai.library.utils;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.markzhai.library.framework.BaseApplication;
import com.markzhai.library.framework.core.model.MZModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marktlzhai on 2015/3/18.
 */
public class DBUtils {

    private static void checkORMSupport() {
        if (!BaseApplication.ormSupport) {
            throw new RuntimeException("不支持ORM插入操作，请参考BaseApplication.initORM()");
        }
    }

    public static void insertAndUpdate(Model model) {
        checkORMSupport();
        model.save();
    }

    public static void insertAndUpdate(List<Model> models) {
        checkORMSupport();
        if (CollectionUtils.notEmpty(models)) {
            ActiveAndroid.beginTransaction();

            try {
                for (Model model : models) {
                    model.save();
                }
            } finally {
                ActiveAndroid.endTransaction();
            }
        }
    }

    public static void delete(Model model) {
        checkORMSupport();
        model.delete();
    }

    public static void deleteAll(List<Model> models) {
        checkORMSupport();
        if (CollectionUtils.notEmpty(models)) {
            ActiveAndroid.beginTransaction();

            try {
                for (Model model : models) {
                    model.delete();
                }
            } finally {
                ActiveAndroid.endTransaction();
            }
        }
    }

    public static <T> T query(java.lang.Class<T> modelClass, String where) {
        T result = (T) new Select().from((Class<? extends Model>) modelClass).where(where).executeSingle();
        return result;
    }

    public static <T> T query(java.lang.Class<T> modelClass, String where, String orderBy) {
        T result = (T) new Select().from((Class<? extends Model>) modelClass).where(where).orderBy(orderBy).executeSingle();
        return result;
    }

    public static <T> List<T> queryAll(java.lang.Class<T> modelClass, String where) {
        List<T> result = new ArrayList<T>();
        result.addAll((java.util.Collection<? extends T>) new Select().from((Class<? extends Model>) modelClass).where(where).execute());
        return result;
    }

    public static <T> List<T> queryAll(java.lang.Class<T> modelClass, String where, String orderBy) {
        List<T> result = new ArrayList<T>();
        result.addAll((java.util.Collection<? extends T>) new Select().from((Class<? extends Model>) modelClass).where(where).orderBy(orderBy).execute());
        return result;
    }
}

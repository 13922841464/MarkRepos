package com.markzhai.library.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * 处理SP的工具类 Created by marktlzhai on 2014/12/24.
 */
public class SPUtils {

	private static final String SP_NAME = "conf";

	private static SharedPreferences sp;

	public static void init(Context context) {
		sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
	}

	public static void putBoolean(String key, boolean value) {
		SharedPreferences.Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static boolean getBoolean(String key, boolean defValue) {
		return sp.getBoolean(key, defValue);
	}

	public static void putString(String key, String value) {
		SharedPreferences.Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getString(String key, String defValue) {
		return sp.getString(key, defValue);
	}

	public static void putStringSet(String key, Set<String> value) {
		SharedPreferences.Editor editor = sp.edit();
		editor.putStringSet(key, value);
		editor.commit();
	}

	public static Set<String> getStringSet(String key, Set<String> defValue) {
		return sp.getStringSet(key, defValue);
	}

	public static void putFloat(String key, float value) {
		SharedPreferences.Editor editor = sp.edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	public static float getFloat(String key, float defValue) {
		return sp.getFloat(key, defValue);
	}

	public static void putInt(String key, int value) {
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public static int getInt(String key, int defValue) {
		return sp.getInt(key, defValue);
	}

	public static void putLong(String key, long value) {
		SharedPreferences.Editor editor = sp.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public static long getLong(String key, long defValue) {
		return sp.getLong(key, defValue);
	}

	public static Map<String, ?> getAll() {
		return sp.getAll();
	}
}

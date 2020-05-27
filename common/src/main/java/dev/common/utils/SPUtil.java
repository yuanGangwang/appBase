package dev.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharePreference工具类，用于进行数据缓存以及获取
 */
public class SPUtil {
    private static String PreferenceName = "";

    private static Context mContext;

    public  static void init(Context context, String nameKey) {
        mContext = context;
        PreferenceName = nameKey;
    }

    /**
     * 缓存字符串
     *
     * @param key   键
     * @param value 字符串数据
     */
    public static void saveString(String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 得到缓存的字符串
     *
     * @param key 键
     * @return String 字符串数据
     */
    public static String getString(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    /**
     * 缓存boolen类型数据
     *
     * @param key   key
     * @param value boolen 类型数据
     */
    public static void saveboolean(String key, boolean value) {
        SharedPreferences sp = mContext.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 得到boolen类型数据
     *
     * @param key 键
     * @return boolean
     */
    public static boolean getBoolean(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, true);
    }

    public static boolean getBooleanDefaultFalse(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    /**
     * 保存int类型数据
     *
     * @param key   键
     * @param value 缓存的整形数据
     */
    public static void saveInt(String key, int value) {
        SharedPreferences sp = mContext.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 得到int类型数据
     *
     * @param key 键
     * @return int 缓存的数据，默认是0
     */
    public static int getInt(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

    /**
     * 保存float类型数据
     *
     * @param key   键
     * @param value 缓存的数据
     */
    public static void saveFloat(String key, float value) {
        SharedPreferences sp = mContext.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * 得到float类型数据
     *
     * @param key 键
     * @return float 缓存的数据，默认是0
     */
    public static float getFloat(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getFloat(key, 0);
    }

    /**
     * 保存long类型数据
     *
     * @param key   键
     * @param value 数据
     */
    public static void saveLong(String key, long value) {
        SharedPreferences sp = mContext.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 得到long类型数据
     *
     * @param key 键
     * @return long 缓存的数据
     */
    public static long getLong(String key) {
        SharedPreferences sp = mContext.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getLong(key, 0);
    }
}

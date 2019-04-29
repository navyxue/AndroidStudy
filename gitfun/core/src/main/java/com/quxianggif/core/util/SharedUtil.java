package com.quxianggif.core.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.quxianggif.core.GifFun;

/**
 * SharedPreferences工具类，提供简单的封装接口，简化SharedPreferences的用法
 * @author xuehj
 * @since 2019-04-22
 */
public class SharedUtil {

    /**
     * 存储boolean类型的键值对到SharedPreferences文件当中。
     * @param key 存储的键
     * @param value 存储的值
     */
    public static void save(String key, boolean value) {
        SharedPreferences.Editor editor = PreferenceManager.
                getDefaultSharedPreferences(GifFun.getContext()).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 存储float类型的键值对到SharedPreferences文件当中。
     * @param key 存储的键
     * @param value 存储的值
     */
    public static void save(String key, float value) {
        SharedPreferences.Editor editor = PreferenceManager.
                getDefaultSharedPreferences(GifFun.getContext()).edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * 存储int类型的键值对到SharedPreferences文件当中。
     * @param key 存储的键
     * @param value 存储的值
     */
    public static void save(String key, int value) {
        SharedPreferences.Editor editor = PreferenceManager.
                getDefaultSharedPreferences(GifFun.getContext()).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 存储long类型的键值对到SharedPreferences文件当中。
     * @param key 存储的键
     * @param value 存储的值
     */
    public static void save(String key, long value) {
        SharedPreferences.Editor editor = PreferenceManager.
                getDefaultSharedPreferences(GifFun.getContext()).edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * 存储String类型的键值对到SharedPreferences文件当中。
     * @param key 存储的键
     * @param value 存储的值
     */
    public static void save(String key, String value) {
        SharedPreferences.Editor editor = PreferenceManager.
                getDefaultSharedPreferences(GifFun.getContext()).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 从SharedPreferences文件当中读取参数传入主键相应的boolean类型的值
     * @param key 读取的键
     * @param defValue 如果读取不到值，返回的默认值
     * @return boolean类型的值，如果读取不到，则返回默认值
     */
    public static boolean read(String key, boolean defValue) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(GifFun.getContext());
        return prefs.getBoolean(key, defValue);
    }

    /**
     * 从SharedPreferences文件当中读取参数传入主键相应的float类型的值
     * @param key 读取的键
     * @param defValue 如果读取不到值，返回的默认值
     * @return boolean类型的值，如果读取不到，则返回默认值
     */
    public static float read(String key, float defValue) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(GifFun.getContext());
        return prefs.getFloat(key, defValue);
    }

    /**
     * 从SharedPreferences文件当中读取参数传入主键相应的int类型的值
     * @param key 读取的键
     * @param defValue 如果读取不到值，返回的默认值
     * @return boolean类型的值，如果读取不到，则返回默认值
     */
    public static int read(String key, int defValue) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(GifFun.getContext());
        return prefs.getInt(key, defValue);
    }

    /**
     * 从SharedPreferences文件当中读取参数传入主键相应的long类型的值
     * @param key 读取的键
     * @param defValue 如果读取不到值，返回的默认值
     * @return boolean类型的值，如果读取不到，则返回默认值
     */
    public static long read(String key, long defValue) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(GifFun.getContext());
        return prefs.getLong(key, defValue);
    }

    /**
     * 从SharedPreferences文件当中读取参数传入主键相应的String类型的值
     * @param key 读取的键
     * @param defValue 如果读取不到值，返回的默认值
     * @return boolean类型的值，如果读取不到，则返回默认值
     */
    public static String read(String key, String defValue) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(GifFun.getContext());
        return prefs.getString(key, defValue);
    }

    /**
     * 判断SharedPreferences文件当中是否包含指定的值
     * @param key 判断键是否存在
     * @return 键已存在返回true，否则返回false
     */
    public static boolean contains(String key) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(GifFun.getContext());
        return prefs.contains(key);
    }

    /**
     * 清理SharedPreferences文件当中传入键所对应的值
     * @param key 想要清除的键
     */
    public static void clear(String key) {
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(GifFun.getContext()).edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 将SharedPreferences文件中存储的所有值清除
     */
    public static void clearAll() {
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(GifFun.getContext()).edit();
        editor.clear();
        editor.apply();
    }
}

package com.hr190023_salihselimsekerci.utils;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

public class SharedPrefsUtils {
    private SharedPrefsUtils() {}


    public static String getStringPreference(Context context, String key) { //girilen keye göre değer çekiliyor.
        String value = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getString(key, null);
        }
        return value;
    }


    public static boolean setStringPreference(Context context, String key, String value) { //girilen keye, girilen değeri atıyor.
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null && !TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            return editor.commit();
        }
        return false;
    }

    public static int getIntegerPreference(Context context, String key, int defaultValue) { //girilen keye göre değer çekiliyor, eğer herhangi bir değer bulunamazsa, defaultValue değeri atanıyor.
        int value = defaultValue;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getInt(key, defaultValue);
        }
        return value;
    }

    public static boolean setIntegerPreference(Context context, String key, int value) { //girilen keye, girilen değeri atıyor.
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(key, value);
            return editor.commit();
        }
        return false;
    }

}

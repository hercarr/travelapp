package com.qacg.travelapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtils {

    private static SharedPreferenceUtils mSharedPreferenceUtils;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPreferencesEditor;

    private SharedPreferenceUtils(Context context) {
        if (context != null) {
            mSharedPreferences = context.getSharedPreferences("TRAVEL_APP_PREFERENCES", Context.MODE_PRIVATE);
        }
    }

    /**
     * Creates single instance of SharedPreferenceUtils
     *
     * @param context context of Activity or Service
     * @return Returns instance of SharedPreferenceUtils
     */
    public static synchronized SharedPreferenceUtils getInstance(Context context) {
        if (mSharedPreferenceUtils == null) {
            mSharedPreferenceUtils = new SharedPreferenceUtils(context.getApplicationContext());
        }
        return mSharedPreferenceUtils;
    }

    /**
     * Stores String value in preference
     *
     * @param key   key of preference
     * @param value value for that key
     */
    public void setValue(String key, String value) {
        mSharedPreferencesEditor = mSharedPreferences.edit();
        mSharedPreferencesEditor.putString(key, value);
        mSharedPreferencesEditor.apply();
    }

    /**
     * Stores int value in preference
     *
     * @param key   key of preference
     * @param value value for that key
     */
    public void setValue(String key, int value) {
        mSharedPreferencesEditor = mSharedPreferences.edit();
        mSharedPreferencesEditor.putInt(key, value);
        mSharedPreferencesEditor.apply();
    }

    /**
     * Stores Double value in String format in preference
     *
     * @param key   key of preference
     * @param value value for that key
     */
    public void setValue(String key, double value) {
        mSharedPreferencesEditor = mSharedPreferences.edit();
        setValue(key, Double.toString(value));
        mSharedPreferencesEditor.apply();
    }

    /**
     * Stores long value in preference
     *
     * @param key   key of preference
     * @param value value for that key
     */
    public void setValue(String key, long value) {
        mSharedPreferencesEditor = mSharedPreferences.edit();
        mSharedPreferencesEditor.putLong(key, value);
        mSharedPreferencesEditor.apply();
    }

    /**
     * Stores boolean value in preference
     *
     * @param key   key of preference
     * @param value value for that key
     */
    public void setValue(String key, boolean value) {
        mSharedPreferencesEditor = mSharedPreferences.edit();
        mSharedPreferencesEditor.putBoolean(key, value);
        mSharedPreferencesEditor.apply();
    }

    /**
     * Retrieves String value from preference
     *
     * @param key          key of preference
     * @param defaultValue default value if no key found
     */
    public String getStringValue(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    /**
     * Retrieves int value from preference
     *
     * @param key          key of preference
     * @param defaultValue default value if no key found
     */
    public int getIntValue(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    /**
     * Retrieves long value from preference
     *
     * @param key          key of preference
     * @param defaultValue default value if no key found
     */
    public long getLongValue(String key, long defaultValue) {
        return mSharedPreferences.getLong(key, defaultValue);
    }

    /**
     * Retrieves boolean value from preference
     *
     * @param keyFlag      key of preference
     * @param defaultValue default value if no key found
     */
    public boolean getBoolanValue(String keyFlag, boolean defaultValue) {
        return mSharedPreferences.getBoolean(keyFlag, defaultValue);
    }

    /**
     * Removes key from preference
     *
     * @param key key of preference that is to be deleted
     */
    public void removeKey(String key) {
        if (mSharedPreferencesEditor != null) {
            mSharedPreferencesEditor.remove(key);
            mSharedPreferencesEditor.commit();
        }
    }

    /**
     * Clears all the preferences stored
     */
    public void clear() {
        mSharedPreferencesEditor = mSharedPreferences.edit();
        mSharedPreferencesEditor.clear().apply();
    }

}



package com.example.employeedetails.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import java.util.Set;

/**
 * Preference Util class to store the value in preference.
 */
public class PreferenceUtil {

    /**
     * PreferenceUtil singleton mInstance variable.
     */
    private static PreferenceUtil mInstance;

    /**
     * SharedPreferences object.
     */
    private SharedPreferences mSharedPrefs;

    /**
     * Preference editor object.
     */
    private Editor mPrefsEditor;

    /**
     * Password for encryption/Decryption
     */
    private String mPassword;

    /**
     * Constructor for PreferenceUtil.
     *
     * @param context
     */
    private PreferenceUtil(final Context context, String name, String password) {
        mSharedPrefs = context.getSharedPreferences(name, Activity.MODE_PRIVATE);
        mPrefsEditor = mSharedPrefs.edit();
        mPassword = password;
    }

    /**
     * Initialize at starting of the application using application context.
     *
     * @param application application object.
     */
    public static void init(final Application application, String preferenceName, String password) {
        if (mInstance == null) {
            mInstance = new PreferenceUtil(application, preferenceName, password);
        }
    }

    /**
     * Get the PreferenceUtil singleton object.
     *
     * @return PreferenceUtil object
     */
    public static PreferenceUtil getInstance() {
        if (mInstance == null) {
            throw new RuntimeException("Must run init(Application application)"
                    + " before an mInstance can be obtained");
        }
        return mInstance;
    }

    /**
     * clear all the data from preferences..
     */
    public void clear() {
        mPrefsEditor.clear();
        mPrefsEditor.commit();
    }

    /**
     * remove data from preferences..
     *
     * @param key
     */
    public void remove(String key) {
        mPrefsEditor.remove(key);
        mPrefsEditor.commit();
    }


    /**
     * To set the value as encrypted data in preference
     *
     * @param key
     * @param value
     */
    public void setEncStringValue(final String key, final String value) {

        String encryptedValue = value;
        try {
            encryptedValue = EncryptionUtil.encrypt(value, mPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mPrefsEditor.putString(key, encryptedValue);
        mPrefsEditor.commit();
    }

    /**
     * To get the encrypted String using key
     *
     * @param key
     * @param defaultvalue
     * @return
     */
    public String getEncStringValue(final String key, final String defaultvalue) {
        String encryptedValue = mSharedPrefs.getString(key, defaultvalue);

        try {
            return EncryptionUtil.decrypt(encryptedValue, mPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * To get the Stored string value in Preference.
     *
     * @param key
     * @param defaultvalue
     * @return stored string value.
     */
    public String getStringValue(final String key, final String defaultvalue) {
        return mSharedPrefs.getString(key, defaultvalue);
    }

    /**
     * To store the string value in prefernce.
     *
     * @param key
     * @param value
     */
    public void setStringValue(final String key, final String value) {
        mPrefsEditor.putString(key, value);
        mPrefsEditor.commit();
    }

    /**
     * To get the stored integer value in the preference.
     *
     * @param key
     * @param defaultvalue
     * @return stored integer value.
     */
    public int getIntValue(final String key, final int defaultvalue) {
        return mSharedPrefs.getInt(key, defaultvalue);
    }

    /**
     * To stored the integer value in preference.
     *
     * @param key
     * @param value
     */
    public void setIntValue(final String key, final int value) {
        mPrefsEditor.putInt(key, value);
        mPrefsEditor.commit();
    }

    /**
     * To get the stored long value in the preference.
     *
     * @param key
     * @param defaultvalue
     * @return stored long value.
     */
    public long getLongValue(final String key, final long defaultvalue) {
        return mSharedPrefs.getLong(key, defaultvalue);
    }

    /**
     * To stored the long value in preference.
     *
     * @param key
     * @param value
     */
    public void setLongValue(final String key, final long value) {
        mPrefsEditor.putLong(key, value);
        mPrefsEditor.commit();
    }

    /**
     * To get the stored boolean value from the preference.
     *
     * @param key
     * @param defaultvalue
     * @return
     */
    public boolean getBooleanValue(final String key, final Boolean defaultvalue) {
        return mSharedPrefs.getBoolean(key, defaultvalue);
    }

    /**
     * To set the stored boolean value in preference.
     *
     * @param key
     * @param value
     */
    public void setBooleanValue(final String key, final boolean value) {
        mPrefsEditor.putBoolean(key, value);
        mPrefsEditor.commit();
    }

    /**
     * This one is added in API level 11. Set a set of String values in the preferences editor
     *
     * @param key
     * @param values
     */
    public void putStringSet(String key, Set<String> values) {
        mPrefsEditor.putStringSet(key, values);
        mPrefsEditor.commit();
    }

    /**
     * This one is added in API level 11. get a set of string values..
     *
     * @param key
     * @param defaultvalue
     * @return
     */
    public Set<String> getStringset(String key, Set<String> defaultvalue) {
        return mSharedPrefs.getStringSet(key, defaultvalue);
    }

    /**
     * To get the Stored float value in Preference.
     *
     * @param key
     * @param defaultvalue
     * @return stored string value.
     */
    public float getFloatValue(final String key, final float defaultvalue) {
        return mSharedPrefs.getFloat(key, defaultvalue);
    }

    /**
     * To store the float value in prefernce.
     *
     * @param key
     * @param value
     */
    public void setFloatValue(final String key, final float value) {
        mPrefsEditor.putFloat(key, value);
        mPrefsEditor.commit();
    }
}

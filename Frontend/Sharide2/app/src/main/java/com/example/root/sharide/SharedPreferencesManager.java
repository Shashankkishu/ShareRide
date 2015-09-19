package com.example.root.sharide;


import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferencesManager {

    private static String prefName = "hopon";
    private static String prefName_debug = "O1_debug";
    private static SharedPreferencesManager instance;
    private SharedPreferences prefs;

    public static void setPreferencesName(String prefFileName) {
        if (prefFileName == null) {
            throw new IllegalArgumentException("Preferences file name cannot be nnull");
        }

        SharedPreferencesManager.prefName = prefFileName;
    }

    private SharedPreferencesManager(Context context) {
        if (AppClient.isDebuggable) {
            prefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        } else {
            prefs = context.getSharedPreferences(prefName_debug, Context.MODE_PRIVATE);
        }
    }

    public synchronized static SharedPreferencesManager get(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesManager(context);
        }
        return instance;
    }

    public boolean contains(String key) {
        return prefs.contains(key);
    }

    public String getString(String key) {
        String value = prefs.getString(key, null);
        return value;
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public int getInt(String key) {
        int value = prefs.getInt(key, 0);
        return value;
    }

    public int getAppVersion(String key, int value) {
        int version = prefs.getInt(key, value);
        return value;
    }


    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }


    public boolean getBoolean(String key) {
        boolean value = prefs.getBoolean(key, false);
        return value;
    }


    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public long getLong(String key) {
        long value = prefs.getLong(key, 0);
        return value;
    }


    public void setLong(String key, long value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(key, value);
        editor.commit();
    }


    public void delete(String key) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.commit();
    }

    public void clear(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear().apply();
    }
}

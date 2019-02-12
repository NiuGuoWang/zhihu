package com.example.geekmvp.mygeek.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.geekmvp.mygeek.apps.Constants;
import com.example.geekmvp.mygeek.apps.MyApp;


/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @description:
 */

public class ImplPreferencesHelper  {

    //默认夜间模式为false
    private static final boolean DEFAULT_NIGHT_MODE = false;
    //默认无图模式为false
    private static final boolean DEFAULT_NO_IMAGE = false;
    //默认自动缓存为true
    private static final boolean DEFAULT_AUTO_SAVE = true;
    private static final boolean DEFAULT_LIKE_POINT = false;
    private static final boolean DEFAULT_VERSION_POINT = false;
    private static final boolean DEFAULT_MANAGER_POINT = false;

    private static final int DEFAULT_CURRENT_ITEM = 101;

    private static final String SHAREDPREFERENCES_NAME = "my_sp";

    private final SharedPreferences mSPrefs;

    public ImplPreferencesHelper() {
        mSPrefs = MyApp.app.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public boolean getNightModeState() {
        return mSPrefs.getBoolean(Constants.SP_NIGHT_MODE, DEFAULT_NIGHT_MODE);
    }

    public void setNightModeState(boolean state) {
        mSPrefs.edit().putBoolean(Constants.SP_NIGHT_MODE, state).apply();
    }

    public boolean getNoImageState() {
        return mSPrefs.getBoolean(Constants.SP_NO_IMAGE, DEFAULT_NO_IMAGE);
    }

    public void setNoImageState(boolean state) {
        mSPrefs.edit().putBoolean(Constants.SP_NO_IMAGE, state).apply();
    }

    public boolean getAutoCacheState() {
        return mSPrefs.getBoolean(Constants.SP_AUTO_CACHE, DEFAULT_AUTO_SAVE);
    }

    public void setAutoCacheState(boolean state) {
        mSPrefs.edit().putBoolean(Constants.SP_AUTO_CACHE, state).apply();
    }

    public int getCurrentItem() {
        return mSPrefs.getInt(Constants.SP_CURRENT_ITEM, DEFAULT_CURRENT_ITEM);
    }

    public void setCurrentItem(int item) {
        mSPrefs.edit().putInt(Constants.SP_CURRENT_ITEM, item).apply();
    }

    public boolean getLikePoint() {
        return mSPrefs.getBoolean(Constants.SP_LIKE_POINT, DEFAULT_LIKE_POINT);
    }

    public void setLikePoint(boolean isFirst) {
        mSPrefs.edit().putBoolean(Constants.SP_LIKE_POINT, isFirst).apply();
    }

    public boolean getVersionPoint() {
        return mSPrefs.getBoolean(Constants.SP_VERSION_POINT, DEFAULT_VERSION_POINT);
    }

    public void setVersionPoint(boolean isFirst) {
        mSPrefs.edit().putBoolean(Constants.SP_VERSION_POINT, isFirst).apply();
    }

    public boolean getManagerPoint() {
        return mSPrefs.getBoolean(Constants.SP_MANAGER_POINT, DEFAULT_MANAGER_POINT);
    }

    public void setManagerPoint(boolean isFirst) {
        mSPrefs.edit().putBoolean(Constants.SP_MANAGER_POINT, isFirst).apply();
    }
}

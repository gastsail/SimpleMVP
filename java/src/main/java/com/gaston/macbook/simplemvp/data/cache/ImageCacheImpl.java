package com.gaston.macbook.simplemvp.data.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ImageCacheImpl implements ImageCache {

    private SharedPreferences sharedPrefs;

    public ImageCacheImpl(Context context){
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void saveHdUrl(String hdurl) {
      sharedPrefs.edit().putString("hdurl", hdurl).apply();
    }
}

package com.gaston.macbook.kotlinsimplemvp.data.cache

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class ImageCacheImpl constructor(context:Context) : ImageCache {

    var sharedPrefs:SharedPreferences? = null

    init {
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun saveHdUrl(hdurl: String) {
        sharedPrefs?.edit()?.putString("hdurl",hdurl)?.apply()
    }
}
package com.datingapp.app.utils

import android.content.Context

object GetSet {

    private const val PREFS_NAME = "app_prefs"
    private const val KEY_IS_LOGGED = "is_logged"

    fun setLogged(context: Context, logged: Boolean) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_IS_LOGGED, logged).apply()
    }

    fun isLogged(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_IS_LOGGED, false)
    }

}
package com.dznow.project.device

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesAccessor(context: Context) {

    val sp : SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var user_id: String?
        get() = sp.getString("user_id", null)
        set(value) = sp.edit().putString("user_id", value).apply()

    companion object {
        const val PREFS_NAME = "user_prefs"
    }

}
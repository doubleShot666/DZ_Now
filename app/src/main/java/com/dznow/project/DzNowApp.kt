package com.dznow.project

import android.app.Application
import android.content.Context
import com.dznow.project.device.SharedPreferencesAccessor
import com.google.firebase.FirebaseApp


class DzNowApp : Application() {

    override fun onCreate() {
        prefs = SharedPreferencesAccessor(applicationContext)
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }

    init {
        instance = this
    }

    companion object {
        private var instance: DzNowApp? = null
        var prefs: SharedPreferencesAccessor? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}

val prefs: SharedPreferencesAccessor by lazy {
    DzNowApp.prefs!!
}

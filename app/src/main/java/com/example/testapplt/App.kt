package com.example.testapplt

import android.app.Application
import android.content.SharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    companion object {
        lateinit var hashSettings: SharedPreferences
        lateinit var instance: App
            private set
        const val TOKEN = "auth_token"
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        hashSettings = getSharedPreferences(packageName + "_shared_prefs", MODE_PRIVATE)


    }
}
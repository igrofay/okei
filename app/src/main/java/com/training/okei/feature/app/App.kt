package com.training.okei.feature.app

import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        lateinit var appContext: Context
        const val DATABASE = "app_database"
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

}
package com.training.okei.feature.app

import android.app.Application
import android.content.Context
import androidx.work.*
import com.training.okei.data.UserRepository
import com.training.okei.module.workmanager.WorkAuthorization

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
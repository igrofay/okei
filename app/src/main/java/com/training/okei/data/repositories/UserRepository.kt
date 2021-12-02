package com.training.okei.data.repositories

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.training.okei.data.User
import com.training.okei.feature.app.App

object UserRepository {
    private const val KEY_AUT = "KET_AUT"
    val mLiveDataUser = MutableLiveData<User>()
    val needSignIn = mutableStateOf(false)
    fun saveDataAut(dataAut:String?){
        App.appContext
            .getSharedPreferences(App.DATABASE , Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_AUT,dataAut).apply()
    }

    fun getDataAut() : String? {
        return App.appContext
            .getSharedPreferences(App.DATABASE , Context.MODE_PRIVATE)
            .getString(KEY_AUT, null)
    }

}
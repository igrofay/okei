package com.training.okei.feature.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.training.okei.R
import com.training.okei.data.User
import com.training.okei.data.UserAuthData
import com.training.okei.data.UserRepository
import com.training.okei.feature.app.toast
import com.training.okei.module.web.Web
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelMain: ViewModel() {
    private val mLiveDataUser = UserRepository.mLiveDataUser
    val liveDataUser : LiveData<User> get() = mLiveDataUser
    val needSignIn = UserRepository.needSignIn

}
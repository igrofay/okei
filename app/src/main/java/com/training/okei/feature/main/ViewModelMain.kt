package com.training.okei.feature.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.training.okei.data.User
import com.training.okei.data.repositories.UserRepository

class ViewModelMain: ViewModel() {
    private val mLiveDataUser = UserRepository.mLiveDataUser
    val liveDataUser : LiveData<User> get() = mLiveDataUser
    val needSignIn = UserRepository.needSignIn

}
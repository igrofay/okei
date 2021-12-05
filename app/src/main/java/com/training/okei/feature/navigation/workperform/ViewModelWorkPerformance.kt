package com.training.okei.feature.navigation.workperform

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.okei.data.Evaluation
import com.training.okei.data.User
import com.training.okei.data.repositories.UserRepository
import com.training.okei.data.repositories.WorkPerformanceRepository
import com.training.okei.feature.app.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelWorkPerformance
    : ViewModel() {
        private val workPerformanceRepository = WorkPerformanceRepository()
        val liveDataUser: LiveData<User> = UserRepository.mLiveDataUser
        val stateListMonths  =  workPerformanceRepository.mStateListMonths
        val stateListTeachers = workPerformanceRepository.mStateListTeachers
        val stateListEvaluations = workPerformanceRepository.mStateListEvaluations

    fun getListMonths(){
        viewModelScope.launch(Dispatchers.IO){
            liveDataUser.value?.let {
                workPerformanceRepository
                    .getMonths(it.token)
            } ?: toast("Проблема с учетной записью")
        }
    }
    fun getListTeachers(nameMonth: String){
        viewModelScope.launch(Dispatchers.IO) {
            liveDataUser.value?.let {
                workPerformanceRepository
                    .getTeachers(it.token , nameMonth)
            } ?: toast("Проблема с учетной записью")
        }
    }

    fun getTeacherPerformance(
        nameMonth: String, login : String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            liveDataUser.value?.let {
                workPerformanceRepository
                    .getListEvaluation(
                        it.token, nameMonth, login
                    )
            } ?: toast("Проблема с учетной записью")
        }
    }
    fun pushChanges(
        nameMonth: String, login : String, list: List<Evaluation?>
    ){
        viewModelScope.launch(Dispatchers.IO) {
            liveDataUser.value?.let {
                workPerformanceRepository
                    .pushChanges(
                        it.token, nameMonth, login, list
                    )
            } ?: toast("Проблема с учетной записью")
        }
    }
}
package com.training.okei.module.workmanager

import android.content.Context
import android.util.Log
import androidx.work.*
import com.google.gson.Gson
import com.training.okei.R
import com.training.okei.data.User
import com.training.okei.data.UserAuthData
import com.training.okei.data.UserRepository
import com.training.okei.feature.app.toast
import com.training.okei.module.web.Web
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class WorkAuthorization(appContext: Context, params: WorkerParameters)
    : CoroutineWorker(appContext, params)
{
    override suspend fun doWork(): Result {
        val dataAut = UserRepository.getDataAut()
        if (dataAut == null) {
            UserRepository.needSignIn.value = true
            return Result.failure()
        }
        val response = Web.server.authentication(Gson().fromJson(dataAut, UserAuthData::class.java))
        when {
            response.code() ==401 -> {
                withContext(Dispatchers.Main){
                    UserRepository.saveDataAut(null)
                    toast(R.string.data_error)
                    UserRepository.needSignIn.value = true
                }
                return Result.failure()
            }
            response.code() == 500 -> {
                withContext(Dispatchers.Main){
                    toast(R.string.server_error)
                }
                return Result.failure()
            }
            else -> {
                UserRepository.needSignIn.value =false
                UserRepository.mLiveDataUser.postValue(response.body())
            }
        }
        return Result.success()
    }

    companion object {
        const val TAG_WORK = "WORK_AUT"
        fun start(context: Context) {
            val constraint  = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            val work = OneTimeWorkRequestBuilder<WorkAuthorization>()
                .setConstraints(constraint)
                .build()
            WorkManager.getInstance(context).beginUniqueWork(TAG_WORK , ExistingWorkPolicy.REPLACE, work).enqueue()
        }

    }
}
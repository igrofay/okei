package com.training.okei.data.repositories

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.training.okei.data.Month
import com.training.okei.data.Teacher
import com.training.okei.feature.app.toast
import com.training.okei.module.web.Web
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkPerformanceRepository {
    val mStateListMonths = mutableStateListOf<Month>()
    val mStateListTeachers = mutableStateListOf<Teacher>()

    suspend fun getMonths(token : String){
        try {
            val result = Web.server.monthsList(token)
            withContext(Dispatchers.Main){
                result.body()?.let {
                    putValueMonths(it)
                } ?:
                    toast(result.code().toString())
            }
        }catch (e: Throwable){
            withContext(Dispatchers.Main){
                toast("Проблема с сетью")
            }
        }
    }

    private fun putValueMonths(list: List<Month>){
        if (mStateListMonths.size == 0){
            mStateListMonths.addAll(list)
        }else{
            mStateListMonths.forEachIndexed{
                index, month ->
                val monthNew = list[index]
                month.progress = monthNew.progress
                month.lastChange = monthNew.lastChange
                month.leader = monthNew.leader
                month.underway = monthNew.underway
            }
        }
    }

    suspend fun getTeachers(token: String , nameMonth:String){
        mStateListTeachers.clear()
        try {
            val result = Web.server.teacherList(token , nameMonth)
            withContext(Dispatchers.Main){
                result.body()?.let {
                    mStateListTeachers.addAll(it)
                } ?:
                toast(result.code().toString())
            }
        }catch (e: Throwable){
            withContext(Dispatchers.Main){
                toast("Проблема с сетью")
            }
        }
    }




}
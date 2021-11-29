package com.training.okei.module.web

import com.training.okei.data.User
import com.training.okei.data.UserAuthData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ServerAPI {
    @POST("login")
    suspend fun authentication(@Body data: UserAuthData) : Response<User>
}
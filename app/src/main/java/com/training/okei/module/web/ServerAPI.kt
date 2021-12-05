package com.training.okei.module.web

import com.training.okei.data.*
import retrofit2.Response
import retrofit2.http.*


interface ServerAPI {
    @POST("login")
    suspend fun authentication(@Body data: UserAuthData) : Response<User>

    @GET("months")
    suspend fun monthsList( @Header("authorization")   token : String) : Response<List<Month>>

    @GET("months/{name}")
    suspend fun teacherList(@Header("Authorization")  token : String, @Path("name") nameMonth: String) : Response<List<Teacher>>

    @GET("months/{name}/{login}")
    suspend fun teacherPerformance(
        @Header("Authorization")  token : String,
        @Path("name") nameMonth: String,
        @Path("login") loginTeacher : String
    ) : Response<List<Evaluation?>>

    @PUT("months/{name}/{login}")
    suspend fun pushChanges(
        @Header("Authorization")  token : String,
        @Path("name") nameMonth: String,
        @Path("login") loginTeacher : String,
        @Body list: List<Evaluation?>
    ) : Response<List<Evaluation?>>

}
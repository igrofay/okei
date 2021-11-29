package com.training.okei.module.web

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Web {
    private const val SERVER_URL = "http://176.28.64.201:3434/"
    private val client: Retrofit by lazy {
        Retrofit.Builder().baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val server: ServerAPI by lazy {
        client.create(ServerAPI::class.java)
    }


}
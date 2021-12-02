package com.training.okei.data

data class User(
    var login: String,
    val name: String,
    val imageURL: String,
    val status: String,
    val token: String
)

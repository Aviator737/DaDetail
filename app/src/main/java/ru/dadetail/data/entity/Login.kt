package ru.dadetail.data.entity

import com.google.gson.annotations.SerializedName

class Login(
    @SerializedName("login") val login: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("password") val password: String
)
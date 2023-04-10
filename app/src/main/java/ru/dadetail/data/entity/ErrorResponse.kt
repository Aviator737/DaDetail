package ru.dadetail.data.entity

import com.google.gson.annotations.SerializedName

class ErrorResponse(
    @SerializedName("id") val error: String,
    @SerializedName("errorMessage") val errorMessage: String
)


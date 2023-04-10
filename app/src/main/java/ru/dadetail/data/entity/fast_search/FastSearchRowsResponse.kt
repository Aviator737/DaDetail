package ru.dadetail.data.entity.fast_search

import com.google.gson.annotations.SerializedName

class FastSearchRowsResponse(
    @SerializedName("nonOriginalAnalog") val nonOriginalAnalog: List<FastSearchRowResponse>?,
    @SerializedName("originalAnalog") val originalAnalog: List<FastSearchRowResponse>?,
    @SerializedName("request") val request: List<FastSearchRowResponse>?
)
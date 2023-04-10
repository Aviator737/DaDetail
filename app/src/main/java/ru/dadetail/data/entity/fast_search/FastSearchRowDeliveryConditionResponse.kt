package ru.dadetail.data.entity.fast_search

import com.google.gson.annotations.SerializedName

class FastSearchRowDeliveryConditionResponse(
    @SerializedName("type") val type: String?,
    @SerializedName("title") val title: String?
)
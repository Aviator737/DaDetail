package ru.dadetail.data.entity

import com.google.gson.annotations.SerializedName

class CartUpdatePositionResponse(
    @SerializedName("data") val data: CartPositionResponse,
)

class CartPositionResponse(
    @SerializedName("positionID") val positionID: Long,
)
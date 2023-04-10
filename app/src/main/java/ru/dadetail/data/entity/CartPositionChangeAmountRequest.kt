package ru.dadetail.data.entity

import com.google.gson.annotations.SerializedName

class CartPositionChangeAmountRequest(
    @SerializedName("positionID") val positionID: Long,
    @SerializedName("amount") val amount: Int,
)
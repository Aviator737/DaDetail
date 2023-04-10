package ru.dadetail.data.entity

import com.google.gson.annotations.SerializedName

class RemovePositionFromCartRequest(
    @SerializedName("positionID") val positionID: Long,
)
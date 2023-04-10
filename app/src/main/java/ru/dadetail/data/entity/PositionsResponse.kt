package ru.dadetail.data.entity

import com.google.gson.annotations.SerializedName

class PositionsResponse(
    @SerializedName("data") val data: List<PositionResponse>
)

class PositionResponse(
    @SerializedName("positionID") val positionID: Long,
    @SerializedName("orderID") val orderID: Long,
    @SerializedName("brand") val brand: String?,
    @SerializedName("article") val article: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("amount") val amount: Double?,
    @SerializedName("price") val price: Double?,
    @SerializedName("sum") val sum: Double?,
    @SerializedName("payedSum") val payedSum: Double?,
    @SerializedName("debtSum") val debtSum: Double?,
    @SerializedName("statusID") val statusID: String?,
    @SerializedName("statusName") val statusName: String?,
    @SerializedName("destination") val destination: String?,
    @SerializedName("term") val term: String?,
    @SerializedName("maxTerm") val maxTerm: String?,
    @SerializedName("arrivalDate") val arrivalDate: String?
)
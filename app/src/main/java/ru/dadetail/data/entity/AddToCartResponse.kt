package ru.dadetail.data.entity

import com.google.gson.annotations.SerializedName

class AddToCartResponse(
    @SerializedName("addedPositionId") val addedPositionId: Long,
    @SerializedName("all_count") val allCount: Int,
    @SerializedName("amount") val amount: Int,
    @SerializedName("amounts_count") val amountsCount: Int,
    @SerializedName("cid") val cid: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("status") val status: Int,
    @SerializedName("infoSummaryBasket") val infoSummaryBasket: String,
    @SerializedName("sumRaw") val sumRaw: Double,
    @SerializedName("sum_display") val sum_display: String,
    @SerializedName("summ") val summ: String,
    @SerializedName("termView") val termView: String
)
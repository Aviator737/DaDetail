package ru.dadetail.data.entity

import com.google.gson.annotations.SerializedName

class OrdersResponse(
    @SerializedName("data") val data: List<OrdersResponseItem>
)

class OrdersResponseItem(
    @SerializedName("orderID") val orderID: String?,
    @SerializedName("orderNumber") val orderNumber: String?,
    @SerializedName("paymentTypeName") val paymentTypeName: String?,
    @SerializedName("deliveryTypeName") val deliveryTypeName: String?,
    @SerializedName("deliveryOrderAddress") val deliveryOrderAddress: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("comment") val comment: String?,
    @SerializedName("positionsQuantity") val positionsQuantity: Double?,
    @SerializedName("positionsAmount") val positionsAmount: Double?,
    @SerializedName("sum") val sum: Double?,
    @SerializedName("currencyISO") val currencyISO: String?,
    @SerializedName("dateTime") val dateTime: String?
)
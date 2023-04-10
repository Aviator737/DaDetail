package ru.dadetail.data.entity

import com.google.gson.annotations.SerializedName

class CreateOrderResponse(
    @SerializedName("orderData") val orderData: CreateOrderData
)

class CreateOrderData(
    @SerializedName("orderId") val orderId: Long,
    @SerializedName("positions") val positions: List<CreateOrderPosition>,
    @SerializedName("orderSum") val orderSum: Double,
    @SerializedName("deliverySum") val deliverySum: Double,
    @SerializedName("paymentKindsId") val paymentKindsId: Int,
    @SerializedName("deliveryId") val deliveryId: Int,
    @SerializedName("addressId") val addressId: Int,
    @SerializedName("cityId") val cityId: Int,
    @SerializedName("districtId") val districtId: Int,
    @SerializedName("contact") val contact: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("carId") val carId: Int,
    @SerializedName("pointId") val pointId: Int,
)

class CreateOrderPosition(
    @SerializedName("id") val id: Long,
    @SerializedName("article") val article: String,
    @SerializedName("brand") val brand: String,
    @SerializedName("name") val name: String,
    @SerializedName("amount") val amount: String,
    @SerializedName("price") val price: Double,
    @SerializedName("sum") val sum: Double,
    @SerializedName("image") val image: String?,
    @SerializedName("checkRelevance") val checkRelevance: Boolean,
)

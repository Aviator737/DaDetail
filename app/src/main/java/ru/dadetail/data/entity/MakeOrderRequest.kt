package ru.dadetail.data.entity

import com.google.gson.annotations.SerializedName

class MakeOrderRequest(
    @SerializedName("address") val address: String?,
    @SerializedName("addressId") val addressId: Int,
    @SerializedName("carId") val carId: Int,
    @SerializedName("cityId") val cityId: Int,
    @SerializedName("comment") val comment: String,
    @SerializedName("contact") val contact: String,
    @SerializedName("deliveryId") val deliveryId: Int,
    @SerializedName("deliveryTariffId") val deliveryTariffId: Int,
    @SerializedName("districtId") val districtId: Int?,
    @SerializedName("email") val email: String?,
    @SerializedName("isPickup") val isPickup: Boolean,
    @SerializedName("needValidation") val needValidation: Boolean,
    @SerializedName("orderId") val orderId: Long,
    @SerializedName("paymentFromBalanceIsActive") val paymentFromBalanceIsActive: Boolean,
    @SerializedName("paymentKindsId") val paymentKindsId: Int,
    @SerializedName("phone") val phone: String,
    @SerializedName("pointId") val pointId: Int,
    @SerializedName("positions") val positions: List<Long>
)
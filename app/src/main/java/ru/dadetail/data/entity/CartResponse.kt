package ru.dadetail.data.entity

import com.google.gson.annotations.SerializedName

class CartResponse(
    @SerializedName("data") val data: CartResponseItems
)

class CartResponseItems(
    @SerializedName("items") val data: List<CartResponseItem>
)

class CartResponseItem(
    @SerializedName("id") val id: Long,
    @SerializedName("brand") val brand: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("article") val article: String?,
    @SerializedName("amount") val amount: String?,
    @SerializedName("termDisplay") val termDisplay: String?,
    @SerializedName("price") val price: Double?,
    @SerializedName("sum") val sum: Double?,
    @SerializedName("image") val image: String?
)
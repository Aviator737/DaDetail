package ru.dadetail.ui.screens.cart

data class CartItemModel(
    val id: Long,
    val name: String,
    val article: String,
    val price: String,
    val termDisplay: String,
    val sum: String,
    val image: String?,
    val isSelected: Boolean
)
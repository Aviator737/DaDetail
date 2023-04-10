package ru.dadetail.ui.screens.orders.orders

class OrderItemModel(
    val orderID: String,
    val orderNumber: String,
    val paymentTypeName: String,
    val deliveryTypeName: String,
    val deliveryOrderAddress: String,
    val phone: String,
    val email: String,
    val comment: String,
    val positionsQuantity: String,
    val positionsAmount: String,
    val sum: String,
    val currencyISO: String,
    val dateTime: String
)
package ru.dadetail.ui.screens.orders.positions

class PositionItemModel(
    val positionID: Long,
    val orderID: Long,
    val brand: String,
    val article: String,
    val description: String,
    val amount: String,
    val price: String,
    val sum: String,
    val payedSum: String,
    val debtSum: String,
    val statusID: String,
    val statusName: String,
    val destination: String,
    val term: String,
    val maxTerm: String,
    val arrivalDate: String
)
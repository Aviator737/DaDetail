package ru.dadetail.ui.screens.orders.orders

import androidx.lifecycle.viewModelScope
import ru.dadetail.data.repository.OrderRepository
import ru.dadetail.ui.base.BaseViewModel
import ru.dadetail.ui.base.Lce
import ru.dadetail.util.extentions.launchSafe

class OrdersViewModel(
    private val orderRepository: OrderRepository
): BaseViewModel<OrdersContract.Event, OrdersContract.State, OrdersContract.Effect>() {
    override fun createInitialState() = OrdersContract.State()

    override fun handleEvent(event: OrdersContract.Event) {
        when(event) {
            else -> {}
        }
    }

    init {
        getOrders()
    }

    private fun getOrders() = viewModelScope.launchSafe(::onError) {
        setState { copy(items = Lce.Loading()) }
        val result = orderRepository.getOrders()

        if (result.isSuccessful) {
            val items = result.body()?.data?.map {
                OrderItemModel(
                    orderID = it.orderID ?: "",
                    orderNumber = it.orderNumber ?: "",
                    paymentTypeName = it.paymentTypeName ?: "",
                    deliveryTypeName = it.deliveryTypeName ?: "",
                    deliveryOrderAddress = it.deliveryOrderAddress ?: "",
                    phone = it.phone ?: "",
                    email = it.email ?: "",
                    comment = it.comment ?: "",
                    positionsQuantity = it.positionsQuantity.toString(),
                    positionsAmount = it.positionsAmount.toString(),
                    sum = it.sum.toString(),
                    currencyISO = it.currencyISO ?: "",
                    dateTime = it.dateTime ?: "",
                )
            } ?: listOf()
            setState { copy(items = Lce.Content(items)) }
        } else {
            setState { copy(items = Lce.Error(null)) }
        }
    }
}
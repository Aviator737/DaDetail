package ru.dadetail.ui.screens.orders.orders

import ru.dadetail.ui.base.Lce
import ru.dadetail.ui.base.UiEffect
import ru.dadetail.ui.base.UiEvent
import ru.dadetail.ui.base.UiState

class OrdersContract {
    sealed class Event : UiEvent {
        object OnGetOrders : Event()
    }

    data class State(
        val items: Lce<List<OrderItemModel>> = Lce.Content(listOf())
    ) : UiState

    sealed class Effect: UiEffect {
        sealed class Navigation: Effect() {
        }
    }
}
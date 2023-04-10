package ru.dadetail.ui.screens.cart

import ru.dadetail.ui.base.Lce
import ru.dadetail.ui.base.UiEffect
import ru.dadetail.ui.base.UiEvent
import ru.dadetail.ui.base.UiState

class CartContract {
    sealed class Event : UiEvent {
        object OnGetCart : Event()
        class OnRemoveFromCart(val id: Long): Event()
        class OnSelectItem(val item: CartItemModel): Event()
        object OnSelectAll: Event()
        object OnSubmit: Event()
    }

    data class State(
        val selectAll: Boolean = false,
        val submitEnabled: Boolean = false,
        val orderInProgress: Boolean = false,
        val items: Lce<List<CartItemModel>> = Lce.Content(listOf())
    ) : UiState

    sealed class Effect: UiEffect {
        object OrderCreatedToast: Effect()
        sealed class Navigation: Effect() {
        }
    }
}
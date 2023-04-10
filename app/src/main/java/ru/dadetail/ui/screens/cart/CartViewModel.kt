package ru.dadetail.ui.screens.cart

import androidx.lifecycle.viewModelScope
import ru.dadetail.data.repository.CartRepository
import ru.dadetail.data.repository.OrderRepository
import ru.dadetail.ui.base.BaseViewModel
import ru.dadetail.ui.base.Lce
import ru.dadetail.util.extentions.launchSafe

class CartViewModel(
    private val cartRepository: CartRepository,
    private val orderRepository: OrderRepository
): BaseViewModel<CartContract.Event, CartContract.State, CartContract.Effect>() {
    override fun createInitialState() = CartContract.State()

    override fun handleEvent(event: CartContract.Event) {
        when(event) {
            is CartContract.Event.OnGetCart -> getCart()
            is CartContract.Event.OnRemoveFromCart -> removeFromCart(event.id)
            is CartContract.Event.OnSelectAll -> selectAll()
            is CartContract.Event.OnSelectItem -> selectItem(event.item)
            is CartContract.Event.OnSubmit -> createOrder()
        }
    }

    init {
        getCart()
    }

    private fun getCart() = viewModelScope.launchSafe(::onError) { loadCart() }

    private suspend fun loadCart() {
        setState { copy(items = Lce.Loading()) }
        val result = cartRepository.getCart()

        if (result.isSuccessful) {
            val items = result.body()?.data?.data?.map {
                CartItemModel(
                    id = it.id,
                    name = it.name ?: "",
                    article = it.article ?: "",
                    price = it.price.toString(),
                    termDisplay = it.termDisplay ?: "",
                    sum = it.sum.toString(),
                    image = it.image ?: "",
                    isSelected = false
                )
            } ?: listOf()
            setState { copy(items = Lce.Content(items)) }
        } else {
            setState { copy(items = Lce.Error(null)) }
        }
    }

    private fun removeFromCart(id: Long) = viewModelScope.launchSafe(::onError) {
        setState { copy(items = Lce.Loading()) }
        cartRepository.removeFromCart(id)
        loadCart()
    }

    private fun selectItem(item: CartItemModel) {
        val items = uiState.value.items
        if (items is Lce.Content) {
            val newList = items.data.toMutableList()
            val itemIndex = items.data.indexOf(item)
            newList[itemIndex] = items.data[itemIndex].copy(isSelected = !item.isSelected)
            setState { copy(items = Lce.Content(newList)) }
            updateSubmitEnabled()
        }
    }

    private fun selectAll() {
        updateSubmitEnabled()
    }

    private fun updateSubmitEnabled() {
        val items = uiState.value.items
        if (items is Lce.Content) {
            setState { copy(submitEnabled = items.data.count { it.isSelected } > 0) }
        }
    }

    private fun createOrder() = viewModelScope.launchSafe(
        onComplete = { setState { copy(orderInProgress = false) } },
        onError = ::onError
    ) {
        setState { copy(orderInProgress = true) }
        val items = uiState.value.items
        if (items is Lce.Content) {
            val positionIds = items.data
                .filter { it.isSelected }
                .map { it.id }
            val result = orderRepository.createOrder(positionIds)
            if (result != null) {
                if (result.isSuccessful) {
                    loadCart()
                    setEffect { CartContract.Effect.OrderCreatedToast }
                }
            }
        }
    }
}
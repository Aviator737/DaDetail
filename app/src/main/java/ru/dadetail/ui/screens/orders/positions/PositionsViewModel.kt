package ru.dadetail.ui.screens.orders.positions

import androidx.lifecycle.viewModelScope
import ru.dadetail.data.repository.PositionRepository
import ru.dadetail.ui.base.BaseViewModel
import ru.dadetail.ui.base.Lce
import ru.dadetail.util.extentions.launchSafe

class PositionsViewModel(
    private val positionRepository: PositionRepository
): BaseViewModel<PositionsContract.Event, PositionsContract.State, PositionsContract.Effect>() {
    override fun createInitialState() = PositionsContract.State()

    override fun handleEvent(event: PositionsContract.Event) {
        when(event) {
            else -> {}
        }
    }

    init {
        getOrders()
    }

    private fun getOrders() = viewModelScope.launchSafe(::onError) {
        setState { copy(items = Lce.Loading()) }
        val result = positionRepository.getPositions()

        if (result.isSuccessful) {
            val items = result.body()?.data?.map {
                PositionItemModel(
                    positionID = it.positionID,
                    orderID = it.orderID,
                    brand = it.brand ?: "",
                    article = it.article ?: "",
                    description = it.description ?: "",
                    amount = it.amount.toString(),
                    price = it.price.toString(),
                    sum = it.sum.toString(),
                    payedSum = it.payedSum.toString(),
                    debtSum = it.debtSum.toString(),
                    statusID = it.statusID ?: "",
                    statusName = it.statusName ?: "",
                    destination = it.destination ?: "",
                    term = it.term ?: "",
                    maxTerm = it.maxTerm ?: "",
                    arrivalDate = it.arrivalDate ?: ""
                )
            } ?: listOf()
            setState { copy(items = Lce.Content(items)) }
        } else {
            setState { copy(items = Lce.Error(null)) }
        }
    }
}
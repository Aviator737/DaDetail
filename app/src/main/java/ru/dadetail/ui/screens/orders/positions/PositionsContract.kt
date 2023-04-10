package ru.dadetail.ui.screens.orders.positions

import ru.dadetail.ui.base.Lce
import ru.dadetail.ui.base.UiEffect
import ru.dadetail.ui.base.UiEvent
import ru.dadetail.ui.base.UiState

class PositionsContract {
    sealed class Event : UiEvent {
        object OnGetPositions : Event()
    }

    data class State(
        val items: Lce<List<PositionItemModel>> = Lce.Content(listOf())
    ) : UiState

    sealed class Effect: UiEffect {
        sealed class Navigation: Effect() {
        }
    }
}

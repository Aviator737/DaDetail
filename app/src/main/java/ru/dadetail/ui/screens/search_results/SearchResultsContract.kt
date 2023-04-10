package ru.dadetail.ui.screens.search_results

import ru.dadetail.ui.base.Lce
import ru.dadetail.ui.base.UiEffect
import ru.dadetail.ui.base.UiEvent
import ru.dadetail.ui.base.UiState
import ru.dadetail.ui.screens.search_results.models.SearchResultListItem

class SearchResultsContract {
    sealed class Event : UiEvent {
        class OnAddToCart(
            val hash: String,
            val sid: String
        ): Event()

        class OnSwitchRowExpanded(
            val item: SearchResultListItem
        ): Event()
    }

    data class State(
        val listContent: Lce<List<SearchResultListItem>> = Lce.Content(listOf()),
    ) : UiState

    sealed class Effect: UiEffect {
        object AddedToCartToast: Effect()
    }
}
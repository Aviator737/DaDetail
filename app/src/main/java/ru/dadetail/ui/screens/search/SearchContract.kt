package ru.dadetail.ui.screens.search

import androidx.compose.ui.text.input.TextFieldValue
import ru.dadetail.ui.base.Lce
import ru.dadetail.ui.base.UiEffect
import ru.dadetail.ui.base.UiEvent
import ru.dadetail.ui.base.UiState

class SearchContract {
    sealed class Event : UiEvent {
        class OnSearchInput(val data: TextFieldValue): Event()
        class OnSearchResultClicked(val data: SearchResultModel): Event()
    }

    data class State(
        val search: TextFieldValue = TextFieldValue(),
        val searchResults: Lce<List<SearchResultModel>> = Lce.Content(listOf()),
    ) : UiState

    sealed class Effect: UiEffect {
        sealed class Navigation: Effect() {
            class ToSearchResults(val article: String, val brand: String): Navigation()
        }
    }
}
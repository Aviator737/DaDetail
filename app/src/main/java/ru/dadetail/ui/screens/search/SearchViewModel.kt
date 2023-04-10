package ru.dadetail.ui.screens.search

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import ru.dadetail.data.repository.SearchRepository
import ru.dadetail.ui.base.BaseViewModel
import ru.dadetail.ui.base.Lce
import ru.dadetail.ui.nav.AppNavigator
import ru.dadetail.ui.nav.Destination
import ru.dadetail.util.extentions.handleErrors

class SearchViewModel(
    private val searchRepository: SearchRepository,
    private val appNavigator: AppNavigator
): BaseViewModel<SearchContract.Event, SearchContract.State, SearchContract.Effect>() {
    override fun createInitialState() = SearchContract.State()
    override fun handleEvent(event: SearchContract.Event) {
        when(event) {
            is SearchContract.Event.OnSearchInput -> {
                setState { copy(search = event.data) }
                searchFlow.tryEmit(event.data.text)
            }
            is SearchContract.Event.OnSearchResultClicked -> {
                appNavigator.tryNavigateTo(
                    Destination.SearchResultsScreen(
                        article = event.data.name,
                        brand = event.data.prdName
                    )
                )
            }
        }
    }

    private val searchFlow = MutableSharedFlow<String>(1, 0, BufferOverflow.DROP_OLDEST)

    init {
        searchFlow.debounce(1300).onEach {
            setState { copy(searchResults = Lce.Content(listOf())) }
            if (it.isNotEmpty()) {
                setState { copy(searchResults = Lce.Loading()) }
                val result = withContext(Dispatchers.IO) {
                    searchRepository.searchSuggestions(
                        term = it
                    )
                }
                if (result.isSuccessful) {
                    val items = result.body()?.map {
                        SearchResultModel(
                            name = it.code ?: "",
                            prdName = it.prdName ?: "",
                            comment = it.comment ?: ""
                        )
                    } ?: listOf()
                    setState { copy(searchResults = Lce.Content(items)) }
                } else {
                    setState { copy(searchResults = Lce.Content(listOf())) }
                }
            }
        }.handleErrors {
            setState { copy(searchResults = Lce.Content(listOf())) }
            onError(it)
        }.launchIn(viewModelScope)
    }
}
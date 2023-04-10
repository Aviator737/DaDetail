package ru.dadetail.ui.screens.search_results

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import ru.dadetail.data.entity.fast_search.FastSearchRowResponse
import ru.dadetail.data.repository.CartRepository

import ru.dadetail.data.repository.SearchRepository
import ru.dadetail.ui.base.BaseViewModel
import ru.dadetail.ui.base.Lce
import ru.dadetail.ui.nav.Destination
import ru.dadetail.ui.screens.search_results.models.*
import ru.dadetail.util.extentions.launchSafe
import ru.dadetail.util.extentions.toBoolean

class SearchResultsViewModel(
    private val searchRepository: SearchRepository,
    private val cartRepository: CartRepository,
    val savedStateHandle: SavedStateHandle
): BaseViewModel<SearchResultsContract.Event, SearchResultsContract.State, SearchResultsContract.Effect>() {
    var article: String = ""
    var brand: String = ""

    override fun createInitialState() = SearchResultsContract.State()

    override fun handleEvent(event: SearchResultsContract.Event) {
        when(event) {
            is SearchResultsContract.Event.OnAddToCart -> addToCart(
                hash = event.hash,
                sid = event.sid
            )
            is SearchResultsContract.Event.OnSwitchRowExpanded -> switchRowExpanded(event.item)
        }
    }

    init {
        article = savedStateHandle.get<String>(Destination.Args.ARTICLE) ?: ""
        brand = savedStateHandle.get<String>(Destination.Args.BRAND) ?: ""

        fastSearch()
    }

    private var searchResults = listOf<SearchResultListItem>()

    private fun fastSearch() = viewModelScope.launchSafe(::onError) {
        setState { copy(listContent = Lce.Loading()) }
        val result = searchRepository.fastSearch(
            article = article,
            brand = brand,
            withAnalogs = true
        )
        if (result.isSuccessful) {
            val data = result.body()?.data ?: return@launchSafe

            val resultList = mutableListOf<SearchResultListItem>()

            val nonOriginalAnalogs = data.rows.nonOriginalAnalog
            val originalAnalog = data.rows.originalAnalog
            val request = data.rows.request

            var i = 0

            request?.let {
                val item = SearchResultListItem.SearchResultRowHeaderModel(
                    RowType.REQUEST,
                    it.size,
                ).applyId(i)
                resultList.add(item)
                val items = getItems(it, item.id)
                resultList.addAll(items)
                i++
            }

            originalAnalog?.let {
                val item = SearchResultListItem.SearchResultRowHeaderModel(
                    RowType.ORIGINAL,
                    it.size
                ).applyId(i)
                resultList.add(item)
                resultList.addAll(getItems(it, item.id))
                i++
            }

            nonOriginalAnalogs?.let {
                val item = SearchResultListItem.SearchResultRowHeaderModel(
                    RowType.NON_ORIGINAL,
                    it.size
                ).applyId(i)
                resultList.add(item)
                resultList.addAll(getItems(it, item.id))
                i++
            }

            searchResults = resultList

            setState { copy(listContent = Lce.Content(resultList)) }
        } else {
            setState { copy(listContent = Lce.Content(listOf())) }
        }
    }

    private fun addToCart(hash: String, sid: String) = viewModelScope.launchSafe(::onError) {
        val result = cartRepository.addToCart(hash = hash, sid = sid)
        if (result.isSuccessful) {
            setEffect { SearchResultsContract.Effect.AddedToCartToast }
        }
    }

    private fun switchRowExpanded(item: SearchResultListItem) {
        val currentListLce = uiState.value.listContent

        if (currentListLce is Lce.Content) {
            val currentList = currentListLce.data

            val itemIndex = currentList.indexOf(item)

            if (item.expanded) {
                currentList[itemIndex].expanded = false
                val newList = currentList.filterNot { it.parentId.contains(item.id) }
                setState { copy(listContent = Lce.Content(newList)) }
            } else {
                val newItems = searchResults.filter { it.parentId.contains(item.id) }
                val insertIndex = itemIndex + 1
                val newList = currentList.toMutableList()
                newList.addAll(insertIndex, newItems)
                currentList[itemIndex].expanded = true
                setState { copy(listContent = Lce.Content(newList)) }
            }
        }
    }

    private fun getOffersByArticle(article: String, offers: List<FastSearchRowResponse>, parentId: String): List<SearchResultListItem> {
        var i = 0
        val articleOffers = offers.filter { it.article == article }.map { offer ->
            val item = SearchResultListItem.SearchResultOfferModel(
                returnType = ReturnType.fromApiValue(offer.isReturnPossible),
                isOfficialDealer = offer.officialDealer?.toBoolean(),
                amount = offer.amount,
                termDelivery = offer.termDelivery,
                price = offer.price,
                hash = offer.hash ?: "",
                sid = offer.id
            ).applyId(i, parentId)
            i++
            item
        }
        return articleOffers
    }

    private fun getItems(offers: List<FastSearchRowResponse>, parentId: String): List<SearchResultListItem> {
        val articleItems = mutableListOf<String>()
        val resultList = mutableListOf<SearchResultListItem>()

        offers.forEachIndexed { i, offer ->
            val article = offer.article ?: ""
            val itemArticleOffers = articleItems.find { it == article }

            if (itemArticleOffers == null) {
                val item = SearchResultListItem.SearchResultRowItemModel(
                    brand = offer.brand ?: "",
                    article = offer.article ?: "",
                    description = offer.name ?: "",
                    image = offer.image
                ).applyId(i, parentId)
                articleItems.add(article)
                resultList.add(item)
                resultList.addAll(getOffersByArticle(article, offers, parentId+"_"+item.id))
            }
        }

        return resultList
    }

    private fun SearchResultListItem.applyId(i: Int, parentIdIn: String = ""): SearchResultListItem {
        return this.apply {
            id += "_${i}"
            parentId = parentIdIn
        }
    }

}

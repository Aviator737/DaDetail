package ru.dadetail.ui.screens.search_results.models

import ru.dadetail.ui.base.ListItem

sealed class SearchResultListItem(
    var id: String,
    var parentId: String,
    var expanded: Boolean
): ListItem {
    class SearchResultRowHeaderModel(
        val rowType: RowType,
        val amount: Int
    ): SearchResultListItem("row_header", "",true)

    class SearchResultRowItemModel(
        val brand: String,
        val article: String,
        val description: String,
        val image: String?
    ): SearchResultListItem("row_item", "", true)

    class SearchResultOfferModel(
        val returnType: ReturnType,
        val isOfficialDealer: Boolean?,
        val amount: String?,
        val termDelivery: String?,
        val price: String?,
        val hash: String,
        val sid: String
    ): SearchResultListItem("row_offer", "",false)
}
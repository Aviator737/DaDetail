package ru.dadetail.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dadetail.data.api.SearchApi

class SearchRepository(private val customerApi: SearchApi) {
    suspend fun searchSuggestions(term: String) = withContext(Dispatchers.IO) {
        customerApi.searchSuggestions(term)
    }

    suspend fun fastSearch(article: String, brand: String, withAnalogs: Boolean) = withContext(Dispatchers.IO) {
        customerApi.fastSearch(article, brand, withAnalogs)
    }
}
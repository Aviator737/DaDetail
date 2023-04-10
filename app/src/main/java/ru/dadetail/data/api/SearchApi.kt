package ru.dadetail.data.api

import retrofit2.Response
import retrofit2.http.*
import ru.dadetail.data.entity.*
import ru.dadetail.data.entity.fast_search.FastSearchResponse

interface SearchApi {
    @GET("search/sphinx/")
    suspend fun searchSuggestions(
        @Query("term") term: String
    ): Response<List<SuggestionResponse>>

    @GET("api/v2/client/fast-search/")
    suspend fun fastSearch(
        @Query("article") article: String,
        @Query("brand") brand: String,
        @Query("withAnalogs") withAnalogs: Boolean
    ): Response<FastSearchResponse>
}
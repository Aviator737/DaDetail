package ru.dadetail.data.entity.fast_search

import com.google.gson.annotations.SerializedName

class FastSearchResponseData(
    @SerializedName("bestOffers") val data: List<FastSearchBestOffersResponse>,
    @SerializedName("brands") val brands: List<FastSearchBrandsResponse>,
    @SerializedName("filters") val filters: List<FastSearchFiltersResponse>,
    @SerializedName("info") val info: FastSearchInfoResponse,
    @SerializedName("rows") val rows: FastSearchRowsResponse,
)

class FastSearchResponse(
    @SerializedName("data") val data: FastSearchResponseData,
)
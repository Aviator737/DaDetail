package ru.dadetail.data.entity

import com.google.gson.annotations.SerializedName

class SuggestionResponse(
    @SerializedName("id") val id: String,
    @SerializedName("code") val code: String?,
    @SerializedName("prd_name") val prdName: String?,
    @SerializedName("comment") val comment: String?,
    @SerializedName("source") val source: String?,
    @SerializedName("sources_count") val sourcesCount: String?,
    @SerializedName("source_phrase") val sourcePhrase: String?
)

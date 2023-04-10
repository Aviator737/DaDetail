package ru.dadetail.ui.screens.search_results.models

enum class ReturnType(val value: String) {
    POSSIBLE("possible"),
    IMPOSSIBLE("impossible"),
    LIMITED("limited"),
    UNKNOWN("unknown");

    companion object {
        fun fromApiValue(apiValue: String?): ReturnType =
            apiValue?.let { apiVal -> values().firstOrNull { it.value == apiVal } } ?: UNKNOWN
    }
}

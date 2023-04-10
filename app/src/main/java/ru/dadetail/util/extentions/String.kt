package ru.dadetail.util.extentions

import com.google.gson.Gson

inline fun <reified T> String.parseObject(
    startString: String,
    endString: String
): T {
    val startIndex = this.indexOf("MakeOrder(") + startString.length
    val trimStart = this.substring(startIndex)

    val endIndex = trimStart.indexOf(endString) + 1
    val trimEnd = trimStart.substring(0, endIndex)

    val gson = Gson()
    return gson.fromJson(trimEnd, T::class.java)
}
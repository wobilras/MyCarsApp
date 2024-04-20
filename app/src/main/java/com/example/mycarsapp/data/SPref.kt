package com.example.mycarsapp.data

import android.content.Context

fun getSearchHistory(context: Context): List<String> {
    val sharedPreferences = context.getSharedPreferences("search_history", Context.MODE_PRIVATE)
    return sharedPreferences.getStringSet("history", setOf())?.toList() ?: listOf()
}

fun addToSearchHistory(context: Context, item: String) {
    val sharedPreferences = context.getSharedPreferences("search_history", Context.MODE_PRIVATE)
    val history = sharedPreferences.getStringSet("history", setOf())?.toMutableSet() ?: mutableSetOf()

    history.remove(item)
    history.add(item)
    if (history.size > 10) {
        history.remove(history.first())
    }
    sharedPreferences.edit().putStringSet("history", history).apply()
}

fun clearSearchHistory(context: Context) {
    val sharedPreferences = context.getSharedPreferences("search_history", Context.MODE_PRIVATE)
    sharedPreferences.edit().remove("history").apply()
}
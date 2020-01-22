package com.github.alunicus.cinemalist.data

data class SearchResult(
    val totalResults: Int,
    val results: List<SearchMovie>
)
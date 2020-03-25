package com.github.alunicus.cinemalist.feature.search.domain.model

data class SearchResult(
    val totalResults: Int,
    val results: List<SearchMovie>
)
package com.github.alunicus.cinemalist.data

data class SearchResult(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val results: List<Movie>
)
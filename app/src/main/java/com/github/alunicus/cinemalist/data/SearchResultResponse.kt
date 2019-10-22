package com.github.alunicus.cinemalist.data

data class SearchResultResponse(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val results: List<Movie>
)
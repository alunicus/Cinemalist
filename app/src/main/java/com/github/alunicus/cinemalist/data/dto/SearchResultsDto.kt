package com.github.alunicus.cinemalist.data.dto

data class SearchResultsDto(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val results: List<SearchMovieDto>
)
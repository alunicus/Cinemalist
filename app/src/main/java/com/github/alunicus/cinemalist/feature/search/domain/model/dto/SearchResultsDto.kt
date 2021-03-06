package com.github.alunicus.cinemalist.feature.search.domain.model.dto

data class SearchResultsDto(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val results: List<SearchMovieDto>
)
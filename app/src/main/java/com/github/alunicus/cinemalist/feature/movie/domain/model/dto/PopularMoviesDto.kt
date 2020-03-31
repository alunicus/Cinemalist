package com.github.alunicus.cinemalist.feature.movie.domain.model.dto

data class PopularMoviesDto(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val results: List<PopularMovieDto>
)
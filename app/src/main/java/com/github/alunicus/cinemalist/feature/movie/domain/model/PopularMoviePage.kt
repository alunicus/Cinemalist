package com.github.alunicus.cinemalist.feature.movie.domain.model

data class PopularMoviePage(
    val pageNumber: Int,
    val pagesTotal: Int,
    val movies: List<PopularMovie>
)
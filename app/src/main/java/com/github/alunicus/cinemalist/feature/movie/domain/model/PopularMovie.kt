package com.github.alunicus.cinemalist.feature.movie.domain.model

data class PopularMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int
)
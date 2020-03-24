package com.github.alunicus.cinemalist.feature.search.model

data class SearchMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int
)
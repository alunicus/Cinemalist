package com.github.alunicus.cinemalist.data

data class SearchMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int
)
package com.github.alunicus.cinemalist.data

data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val originalLanguage: String,
    val popularity: Double,
    val adult: Boolean,
    val budget: Int,
    val homepage: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
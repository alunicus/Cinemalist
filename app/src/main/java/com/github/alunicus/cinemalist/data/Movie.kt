package com.github.alunicus.cinemalist.data

import java.util.*

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
    val releaseDate: Date,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
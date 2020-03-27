package com.github.alunicus.cinemalist.feature.movie.domain.model

import java.util.*

data class MovieDetails(
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
    val backdropPath: String,
    val releaseDate: Date,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
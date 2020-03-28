package com.github.alunicus.cinemalist.feature.movie.domain

import com.github.alunicus.cinemalist.feature.movie.domain.model.MovieDetails
import java.util.*

fun getTestMovieDetailsWithoutNulls() = getTestMovieDetails(
    "http://marvel.com/avengers_movie/",
    "When an unexpected enemy emerges and threatens global safety and security...",
    "/cezWGskPY5x7GaglTTRN4Fugfb8.jpg",
    "/hbn46fQaRmlpBuUrEiFqv0GDL6Y.jpg",
    143
)

fun getTestMovieDetails(
    homepage: String = "",
    overview: String = "",
    posterPath: String = "",
    backdropPath: String = "",
    runtime: Int = 0
): MovieDetails = MovieDetails(
    24428,
    "The Avengers",
    "The Avengers",
    "en",
    33.346,
    false,
    220000000,
    homepage,
    overview,
    posterPath,
    backdropPath,
    Date(1335301200000), //"2012-04-25 00:00:00",
    1519557910,
    runtime,
    "Released",
    false,
    7.7,
    21427
)
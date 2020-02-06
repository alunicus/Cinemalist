package com.github.alunicus.cinemalist

import com.github.alunicus.cinemalist.data.dto.*

fun getSearchMovieDto(backdropPath: String? = null, posterPath: String? = null): SearchMovieDto =
    SearchMovieDto(
        false,
        backdropPath,
        listOf(878, 28, 12),
        24428,
        "en",
        "The Avengers",
        "When an unexpected enemy emerges and threatens global safety and security...",
        7.353212,
        posterPath,
        "2012-04-25",
        "The Avengers",
        false,
        7.33,
        8503
    )
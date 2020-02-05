package com.github.alunicus.cinemalist

import com.github.alunicus.cinemalist.data.dto.*

fun getMovieDto(
    backdropPath: String? = null,
    belongsToCollection: BelongsToCollectionDto? = null,
    homepage: String? = null,
    imdbId: String? = null,
    overview: String? = null,
    posterPath: String? = null,
    runtime: Int? = null,
    tagline: String? = null
) = MovieDto(
    false,
    backdropPath,
    belongsToCollection,
    24428,
    listOf(
        GenreDto(878, "Science Fiction"),
        GenreDto(28, "Action"),
        GenreDto(12, "Adventure")
    ),
    homepage,
    24428,
    imdbId,
    "en",
    "The Avengers",
    overview,
    33.346,
    posterPath,
    listOf(
        ProductionCompanyDto(
            420,
            "Marvel Studios",
            "/hUzeosd33nzE5MCNsZxCGEKTXaQ.png",
            "US"
        )
    ),
    listOf(ProductionCountryDto("US", "United States of America")),
    "2012-04-25",
    1519557910,
    runtime,
    listOf(SpokenLanguageDto("en", "English")),
    "Released",
    tagline,
    "The Avengers",
    false,
    7.7,
    21427
)

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
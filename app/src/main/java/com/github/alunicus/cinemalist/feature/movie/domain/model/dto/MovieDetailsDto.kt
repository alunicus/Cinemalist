package com.github.alunicus.cinemalist.feature.movie.domain.model.dto

import java.util.*

data class MovieDetailsDto(
    val adult: Boolean,
    val backdropPath: String?,
    val belongsToCollection: BelongsToCollectionDto?,
    val budget: Int,
    val genres: List<GenreDto>,
    val homepage: String?,
    val id: Int,
    val imdbId: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompanyDto>,
    val productionCountries: List<ProductionCountryDto>,
    val releaseDate: Date,
    val revenue: Int,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguageDto>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
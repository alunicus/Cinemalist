package com.github.alunicus.cinemalist.feature.movie.domain.model

import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.CastDto
import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.MovieDetailsDto
import com.github.alunicus.cinemalist.feature.search.domain.model.dto.MovieCreditsDto

fun MovieDetailsDto.toMovie() = MovieDetails(
    id,
    title,
    originalTitle,
    originalLanguage,
    popularity,
    adult,
    budget,
    homepage ?: "",
    overview ?: "",
    posterPath ?: "",
    backdropPath ?: "",
    releaseDate,
    revenue,
    runtime ?: 0,
    status,
    video,
    voteAverage,
    voteCount
)

fun MovieCreditsDto.toCast() = cast.toCastList()

private fun List<CastDto>.toCastList() = asSequence().map { it.toCast() }.toList()

private fun CastDto.toCast() = Cast(
    id,
    name,
    character,
    profilePath ?: "",
    order
)
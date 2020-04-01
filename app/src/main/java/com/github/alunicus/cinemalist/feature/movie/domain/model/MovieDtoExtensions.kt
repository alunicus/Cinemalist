package com.github.alunicus.cinemalist.feature.movie.domain.model

import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.*

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

fun PopularMoviesDto.toPopularMovies() = results.toPopularMovieList()

private fun List<PopularMovieDto>.toPopularMovieList() =
    asSequence().map { it.toPopularMovie() }.toList()

private fun PopularMovieDto.toPopularMovie() = PopularMovie(
    id,
    title,
    overview,
    posterPath ?: "",
    popularity,
    voteAverage,
    voteCount
)

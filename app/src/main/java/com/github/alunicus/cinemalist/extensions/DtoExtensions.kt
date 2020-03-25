package com.github.alunicus.cinemalist.extensions

import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.CastDto
import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.MovieDto
import com.github.alunicus.cinemalist.feature.movie.domain.model.Cast
import com.github.alunicus.cinemalist.feature.movie.domain.model.Movie
import com.github.alunicus.cinemalist.feature.search.domain.model.SearchMovie
import com.github.alunicus.cinemalist.feature.search.domain.model.SearchResult
import com.github.alunicus.cinemalist.feature.search.domain.model.dto.*

fun MovieDto.toMovie() =
    Movie(
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

private fun CastDto.toCast() =
    Cast(
        id,
        name,
        character,
        profilePath ?: "",
        order
    )

fun SearchResultsDto.toSearchResult() = SearchResult(
    totalResults,
    results.toSearchMovies()
)

private fun List<SearchMovieDto>.toSearchMovies() = asSequence().map { it.toSearchMovie() }.toList()

private fun SearchMovieDto.toSearchMovie() = SearchMovie(
    id,
    title,
    overview,
    posterPath ?: "",
    popularity,
    voteAverage,
    voteCount
)
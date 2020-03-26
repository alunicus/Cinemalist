package com.github.alunicus.cinemalist.feature.search.domain.model

import com.github.alunicus.cinemalist.feature.search.domain.model.dto.SearchMovieDto
import com.github.alunicus.cinemalist.feature.search.domain.model.dto.SearchResultsDto

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
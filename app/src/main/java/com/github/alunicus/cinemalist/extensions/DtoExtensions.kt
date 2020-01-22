package com.github.alunicus.cinemalist.extensions

import com.github.alunicus.cinemalist.data.Movie
import com.github.alunicus.cinemalist.data.SearchMovie
import com.github.alunicus.cinemalist.data.SearchResult
import com.github.alunicus.cinemalist.data.dto.MovieDto
import com.github.alunicus.cinemalist.data.dto.SearchMovieDto
import com.github.alunicus.cinemalist.data.dto.SearchResultsDto

fun MovieDto.toMovie(): Movie = Movie(
    id,
    title,
    originalTitle,
    originalTitle,
    popularity,
    adult,
    budget,
    homepage ?: "",
    overview ?: "",
    posterPath ?: "",
    releaseDate,
    revenue,
    runtime ?: 0,
    status,
    video,
    voteAverage,
    voteCount
)

fun SearchResultsDto.toSearchResult() = SearchResult(
    totalResults,
    results.toSearchMovies()
)

fun List<SearchMovieDto>.toSearchMovies() = asSequence().map { it.toSearchMovie() }.toList()

fun SearchMovieDto.toSearchMovie() = SearchMovie(
    id,
    title,
    overview,
    posterPath ?: "",
    popularity,
    voteAverage,
    voteCount
)
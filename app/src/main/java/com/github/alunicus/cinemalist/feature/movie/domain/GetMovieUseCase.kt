package com.github.alunicus.cinemalist.feature.movie.domain

import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.core.Result.Failure
import com.github.alunicus.cinemalist.core.Result.Success
import com.github.alunicus.cinemalist.feature.movie.data.MovieRepository
import com.github.alunicus.cinemalist.feature.movie.domain.model.Cast
import com.github.alunicus.cinemalist.feature.movie.domain.model.Movie

class GetMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun getMovie(movieId: Int): Result<Movie, Error> {
        return when (val detailsResult = movieRepository.getMovieDetailsById(movieId)) {
            is Success -> Success(Movie(detailsResult.result, getCast(movieId)))
            is Failure -> detailsResult
        }
    }

    private suspend fun getCast(movieId: Int): List<Cast> {
        return when (val castResult = movieRepository.getMovieCast(movieId)) {
            is Success -> castResult.result
            is Failure -> listOf()
        }
    }
}
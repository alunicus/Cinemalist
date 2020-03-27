package com.github.alunicus.cinemalist.feature.movie.domain

import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.feature.movie.data.MovieRepository
import com.github.alunicus.cinemalist.feature.movie.domain.model.MovieDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun getMovieDetails(movieId: Int): Result<MovieDetails, Error> = withContext(Dispatchers.IO) {
        movieRepository.getMovieDetailsById(movieId)
    }
}
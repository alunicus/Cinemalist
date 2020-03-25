package com.github.alunicus.cinemalist.feature.movie.domain

import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.feature.movie.data.MovieRepository
import com.github.alunicus.cinemalist.feature.movie.domain.model.Cast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMovieCastUseCase(private val movieRepository: MovieRepository) {
    suspend fun getMovieCast(movieId: Int): Result<List<Cast>, Error> =
        withContext(Dispatchers.IO) {
            movieRepository.getMovieCast(movieId)
        }
}
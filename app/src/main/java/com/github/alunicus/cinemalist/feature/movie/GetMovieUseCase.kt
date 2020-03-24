package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.feature.movie.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMovieUseCase(private val movieRepository: MovieRepository) {
    suspend fun getMovie(movieId: Int): Result<Movie, Error> = withContext(Dispatchers.IO) {
        movieRepository.getMovieById(movieId)
    }
}
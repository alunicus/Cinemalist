package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.core.UseCase
import com.github.alunicus.cinemalist.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMovieUseCase(private val movieRepository: MovieRepository, private val movieId: Int) : UseCase<Movie>() {
    override suspend fun run(): Result<Movie, Error> = withContext(Dispatchers.IO) {
        movieRepository.getMovieById(movieId)
    }
}
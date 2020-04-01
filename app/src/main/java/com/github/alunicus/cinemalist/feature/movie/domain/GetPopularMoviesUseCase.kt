package com.github.alunicus.cinemalist.feature.movie.domain

import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.feature.movie.data.MovieRepository
import com.github.alunicus.cinemalist.feature.movie.domain.model.PopularMovie

class GetPopularMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun getPopularMovies(): Result<List<PopularMovie>, Error> =
        movieRepository.getPopularMovies()
}
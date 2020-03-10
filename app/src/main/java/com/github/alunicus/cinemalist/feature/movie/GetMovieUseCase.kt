package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.core.UseCase
import com.github.alunicus.cinemalist.data.Movie

class GetMovieUseCase(private val movieRepository: MovieRepository): UseCase<Movie>() {
    override suspend fun run(): Result<Movie, Error> = movieRepository.getMovieById(458156)
}
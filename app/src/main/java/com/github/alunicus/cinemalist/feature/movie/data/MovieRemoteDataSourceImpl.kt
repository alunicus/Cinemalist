package com.github.alunicus.cinemalist.feature.movie.data

import com.github.alunicus.cinemalist.feature.movie.domain.model.*

class MovieRemoteDataSourceImpl(private val api: MovieApi) : MovieRemoteDataSource {
    override suspend fun getMovieDetailsById(movieId: Int): MovieDetails {
        return api.getMovieDetailsById(movieId).toMovie()
    }

    override suspend fun getMovieCredits(movieId: Int): List<Cast> {
        return api.getMovieCredits(movieId).toCast()
    }

    override suspend fun getPopularMovies(): List<PopularMovie> {
        return api.getPopularMovies().toPopularMovies()
    }
}
package com.github.alunicus.cinemalist.feature.movie.data

import com.github.alunicus.cinemalist.BuildConfig
import com.github.alunicus.cinemalist.feature.movie.domain.model.Cast
import com.github.alunicus.cinemalist.feature.movie.domain.model.MovieDetails
import com.github.alunicus.cinemalist.feature.movie.domain.model.toCast
import com.github.alunicus.cinemalist.feature.movie.domain.model.toMovie

class MovieRemoteDataSourceImpl(private val api: MovieApi) : MovieRemoteDataSource {
    override suspend fun getMovieDetailsById(movieId: Int): MovieDetails {
        return api.getMovieDetailsById(movieId, BuildConfig.API_KEY).toMovie()
    }

    override suspend fun getMovieCredits(movieId: Int): List<Cast> {
        return api.getMovieCredits(movieId, BuildConfig.API_KEY).toCast()
    }
}
package com.github.alunicus.cinemalist.feature.movie.data

import com.github.alunicus.cinemalist.feature.movie.domain.model.Cast
import com.github.alunicus.cinemalist.feature.movie.domain.model.MovieDetails
import com.github.alunicus.cinemalist.feature.movie.domain.model.PopularMovie

interface MovieRemoteDataSource {
    suspend fun getMovieDetailsById(movieId: Int): MovieDetails

    suspend fun getMovieCredits(movieId: Int): List<Cast>

    suspend fun getPopularMovies(): List<PopularMovie>
}
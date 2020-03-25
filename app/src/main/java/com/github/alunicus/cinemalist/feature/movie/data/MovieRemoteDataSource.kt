package com.github.alunicus.cinemalist.feature.movie.data

import com.github.alunicus.cinemalist.feature.movie.model.Cast
import com.github.alunicus.cinemalist.feature.movie.model.Movie

interface MovieRemoteDataSource {
    suspend fun getMovieById(movieId: Int): Movie

    suspend fun getMovieCredits(movieId: Int): List<Cast>
}
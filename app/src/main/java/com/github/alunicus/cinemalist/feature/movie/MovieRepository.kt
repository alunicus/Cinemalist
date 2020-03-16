package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.data.Cast
import com.github.alunicus.cinemalist.data.Movie

interface MovieRepository {
    suspend fun getMovieById(id: Int): Result<Movie, Error>

    suspend fun getMovieCast(id: Int): Result<List<Cast>, Error>
}
package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.data.Movie
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.core.Error

interface MovieRepository {
    suspend fun getMovieById(id: Int): Result<Movie, Error>
}
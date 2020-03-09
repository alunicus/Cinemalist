package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.data.Movie

interface MovieRepository {
    suspend fun getMovieById(id: Int): Movie
}
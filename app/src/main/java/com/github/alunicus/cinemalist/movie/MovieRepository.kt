package com.github.alunicus.cinemalist.movie

import com.github.alunicus.cinemalist.data.Movie

interface MovieRepository {
    suspend fun getMovieById(id: Int): Movie
}
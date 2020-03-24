package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.feature.movie.model.Cast
import com.github.alunicus.cinemalist.feature.movie.model.Movie

data class FullMovie(val movie: Movie, val cast: List<Cast>)
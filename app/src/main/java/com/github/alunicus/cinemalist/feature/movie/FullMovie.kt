package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.data.Cast
import com.github.alunicus.cinemalist.data.Movie

data class FullMovie(val movie: Movie, val cast: List<Cast>)
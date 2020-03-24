package com.github.alunicus.cinemalist.feature.movie.model

data class Cast(
    val id: Int,
    val name: String,
    val character: String,
    val profilePath: String,
    val order: Int
)
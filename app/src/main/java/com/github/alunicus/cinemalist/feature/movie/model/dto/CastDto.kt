package com.github.alunicus.cinemalist.feature.movie.model.dto

data class CastDto(
    val castId: Int,
    val id: Int,
    val name: String,
    val character: String,
    val profilePath: String?,
    val creditId: String,
    val gender: Int?,
    val order: Int
)
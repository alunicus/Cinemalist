package com.github.alunicus.cinemalist.data.dto

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
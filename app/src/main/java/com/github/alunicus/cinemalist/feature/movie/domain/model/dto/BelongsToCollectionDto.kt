package com.github.alunicus.cinemalist.feature.movie.domain.model.dto

data class BelongsToCollectionDto(
    val id: Int,
    val name: String,
    val poster_path: String,
    val backdrop_path: String
)
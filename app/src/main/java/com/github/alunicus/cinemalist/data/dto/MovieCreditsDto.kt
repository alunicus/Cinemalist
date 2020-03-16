package com.github.alunicus.cinemalist.data.dto

data class MovieCreditsDto(
    val id: Int,
    val cast: List<CastDto>,
    val crew: List<CrewDto>
)
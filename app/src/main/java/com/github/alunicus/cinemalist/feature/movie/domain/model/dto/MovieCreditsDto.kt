package com.github.alunicus.cinemalist.feature.movie.domain.model.dto

data class MovieCreditsDto(
    val id: Int,
    val cast: List<CastDto>,
    val crew: List<CrewDto>
)
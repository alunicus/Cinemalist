package com.github.alunicus.cinemalist.feature.search.model.dto

import com.github.alunicus.cinemalist.feature.movie.model.dto.CastDto
import com.github.alunicus.cinemalist.feature.movie.model.dto.CrewDto

data class MovieCreditsDto(
    val id: Int,
    val cast: List<CastDto>,
    val crew: List<CrewDto>
)
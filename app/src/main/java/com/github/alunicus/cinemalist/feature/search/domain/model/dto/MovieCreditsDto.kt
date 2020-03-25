package com.github.alunicus.cinemalist.feature.search.domain.model.dto

import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.CastDto
import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.CrewDto

data class MovieCreditsDto(
    val id: Int,
    val cast: List<CastDto>,
    val crew: List<CrewDto>
)
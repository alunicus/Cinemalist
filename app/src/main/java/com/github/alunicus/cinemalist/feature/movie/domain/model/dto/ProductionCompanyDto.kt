package com.github.alunicus.cinemalist.feature.movie.domain.model.dto

data class ProductionCompanyDto(
    val id: Int,
    val name: String,
    val logo_path: String?,
    val origin_country: String
)
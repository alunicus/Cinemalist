package com.github.alunicus.cinemalist.data.dto

data class CrewDto(
    val id: Int,
    val job: String,
    val name: String,
    val creditId: String,
    val department: String,
    val gender: Int?,
    val profilePath: String?
)
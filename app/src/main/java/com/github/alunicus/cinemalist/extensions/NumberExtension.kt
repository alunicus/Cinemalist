package com.github.alunicus.cinemalist.extensions

import com.github.alunicus.cinemalist.feature.movie.domain.model.Duration

fun Int.toDuration(): Duration {
    val hours = this / 60
    val minutes = this % 60

    return Duration(
        hours,
        minutes
    )
}
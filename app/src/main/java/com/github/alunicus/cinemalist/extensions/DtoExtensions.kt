package com.github.alunicus.cinemalist.extensions

import com.github.alunicus.cinemalist.data.Movie
import com.github.alunicus.cinemalist.data.dto.MovieDto

fun MovieDto.toMovie(): Movie {

    return Movie(
        id,
        title,
        originalTitle,
        originalTitle,
        popularity,
        adult,
        budget,
        homepage ?: "",
        overview ?: "",
        posterPath ?: "",
        releaseDate,
        revenue,
        runtime ?: 0,
        status,
        video,
        voteAverage,
        voteCount
    )
}
package com.github.alunicus.cinemalist.feature.movie.domain.model

import com.github.alunicus.cinemalist.ResourceLoader
import com.github.alunicus.cinemalist.feature.movie.domain.getTestMovieDetails
import com.github.alunicus.cinemalist.feature.movie.domain.getTestMovieDetailsWithoutNulls
import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.MovieDetailsDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieDtoExtensionsTest {
    private val resourceLoader = ResourceLoader("./feature/movie/dto/")

    @Test
    fun `should return Movie from movieDto without null fields`() {
        val actualResult = resourceLoader.readFromJson<MovieDetailsDto>("movie_dto.json").toMovie()

        assertThat(actualResult).isEqualTo(getTestMovieDetailsWithoutNulls())
    }

    @Test
    fun `should return Movie from movieDto with correctly handled null fields`() {
        val movieDto = resourceLoader.readFromJson<MovieDetailsDto>("movie_dto_with_nulls.json")

        assertThat(movieDto.toMovie()).isEqualTo(getTestMovieDetails())
    }
}

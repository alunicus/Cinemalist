package com.github.alunicus.cinemalist.feature.movie.domain.model

import com.github.alunicus.cinemalist.ResourceLoader
import com.github.alunicus.cinemalist.feature.movie.domain.getTestCastList
import com.github.alunicus.cinemalist.feature.movie.domain.getTestCastListWithNulls
import com.github.alunicus.cinemalist.feature.movie.domain.getTestMovieDetails
import com.github.alunicus.cinemalist.feature.movie.domain.getTestMovieDetailsWithNulls
import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.MovieCreditsDto
import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.MovieDetailsDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieDtoExtensionsTest {
    private val resourceLoader = ResourceLoader("./feature/movie/dto/")

    @Test
    fun `should return Movie from movieDto without null fields`() {
        val actualResult = resourceLoader
            .readFromJson<MovieDetailsDto>("movie_dto.json").toMovie()

        assertThat(actualResult).isEqualTo(getTestMovieDetails())
    }

    @Test
    fun `should return Movie from movieDto with correctly handled null fields`() {
        val actualResult = resourceLoader
            .readFromJson<MovieDetailsDto>("movie_dto_with_nulls.json").toMovie()

        assertThat(actualResult).isEqualTo(getTestMovieDetailsWithNulls())
    }

    @Test
    fun `should return Cast list from movieCreditsDto without null fields`() {
        val actualResult = resourceLoader
            .readFromJson<MovieCreditsDto>("movie_credits_dto.json").toCast()

        assertThat(actualResult).isEqualTo(getTestCastList())
    }

    @Test
    fun `should return Cast list from movieCreditsDto with correctly handled null fields`() {
        val actualResult = resourceLoader
            .readFromJson<MovieCreditsDto>("movie_credits_dto_with_nulls.json").toCast()

        assertThat(actualResult).isEqualTo(getTestCastListWithNulls())
    }
}

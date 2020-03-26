package com.github.alunicus.cinemalist.feature.movie.domain.model

import com.github.alunicus.cinemalist.ResourceLoader
import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.MovieDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class MovieDtoExtensionsTest {
    private val resourceLoader = ResourceLoader("./feature/movie/dto/")

    @Test
    fun `should return Movie from movieDto without null fields`() {
        val expectedMovie = getExpectedMovie(
            "http://marvel.com/avengers_movie/",
            "When an unexpected enemy emerges and threatens global safety and security...",
            "/cezWGskPY5x7GaglTTRN4Fugfb8.jpg",
            "/hbn46fQaRmlpBuUrEiFqv0GDL6Y.jpg",
            143
        )

        val actualResult = resourceLoader.readFromJson<MovieDto>("movie_dto.json").toMovie()

        assertThat(actualResult).isEqualTo(expectedMovie)
    }

    @Test
    fun `should return Movie from movieDto with correctly handled null fields`() {
        val movieDto = resourceLoader.readFromJson<MovieDto>("movie_dto_with_nulls.json")

        assertThat(movieDto.toMovie()).isEqualTo(getExpectedMovie())
    }

    private fun getExpectedMovie(
        homepage: String = "",
        overview: String = "",
        posterPath: String = "",
        backdropPath: String = "",
        runtime: Int = 0
    ): Movie {
        return Movie(
            24428,
            "The Avengers",
            "The Avengers",
            "en",
            33.346,
            false,
            220000000,
            homepage,
            overview,
            posterPath,
            backdropPath,
            Date(1335301200000), //"2012-04-25 00:00:00",
            1519557910,
            runtime,
            "Released",
            false,
            7.7,
            21427
        )
    }
}

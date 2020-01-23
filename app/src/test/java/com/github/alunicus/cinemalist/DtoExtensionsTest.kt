package com.github.alunicus.cinemalist

import com.github.alunicus.cinemalist.data.SearchMovie
import com.github.alunicus.cinemalist.data.dto.SearchMovieDto
import com.github.alunicus.cinemalist.extensions.toSearchMovie
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DtoExtensionsTest {
    @Test
    fun `should return SearchMovie converted from SearchMovieDto without null fields`() {
        val searchMovieDto = SearchMovieDto(
            false,
            "/hbn46fQaRmlpBuUrEiFqv0GDL6Y.jpg",
            listOf(878, 28, 12),
            24428,
            "en",
            "The Avengers",
            "When an unexpected enemy emerges and threatens global safety and security...",
            7.353212,
            "/cezWGskPY5x7GaglTTRN4Fugfb8.jpg",
            "2012-04-25",
            "The Avengers",
            false,
            7.33,
            8503
        )

        val expectedSearchMovie = SearchMovie(
            24428,
            "The Avengers",
            "When an unexpected enemy emerges and threatens global safety and security...",
            "/cezWGskPY5x7GaglTTRN4Fugfb8.jpg",
            7.353212,
            7.33,
            8503
        )

        assertThat(expectedSearchMovie).isEqualTo(searchMovieDto.toSearchMovie())
    }

    @Test
    fun `should return SearchMovie converted from SearchMovieDto with all possible null fields`() {
        val searchMovieDto = SearchMovieDto(
            false,
            null,
            listOf(878, 28, 12),
            24428,
            "en",
            "The Avengers",
            "When an unexpected enemy emerges and threatens global safety and security...",
            7.353212,
            null,
            "2012-04-25",
            "The Avengers",
            false,
            7.33,
            8503
        )

        val expectedSearchMovie = SearchMovie(
            24428,
            "The Avengers",
            "When an unexpected enemy emerges and threatens global safety and security...",
            "",
            7.353212,
            7.33,
            8503
        )

        assertThat(expectedSearchMovie).isEqualTo(searchMovieDto.toSearchMovie())
    }
}

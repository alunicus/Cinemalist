package com.github.alunicus.cinemalist

import com.github.alunicus.cinemalist.data.SearchMovie
import com.github.alunicus.cinemalist.data.dto.SearchMovieDto
import com.github.alunicus.cinemalist.extensions.toSearchMovie
import com.github.alunicus.cinemalist.extensions.toSearchMovies
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DtoExtensionsTest {
    @Test
    fun `should return list of SearchMovies from a list of SearchMovieDto`() {
        val searchMovieDtoList = listOf(
            SearchMovieDto(
                false, "bdp1", listOf(1), 1, "en", "OrigTitle1",
                "Overview1", 1.11, "pP1", "2011-01-01", "Title1", false, 1.11, 1
            ),
            SearchMovieDto(
                false, "bdp2", listOf(2), 2, "en", "OrigTitle2",
                "Overview2", 2.22, "pP2", "2012-02-02", "Title2", false, 2.22, 2
            ),
            SearchMovieDto(
                false, "bdp3", listOf(3), 3, "en", "OrigTitle3",
                "Overview3", 3.33, "pP3", "2013-03-03", "Title3", false, 3.33, 3
            )
        )

        val expectedSearchMovieList = listOf(
            SearchMovie(1, "Title1", "Overview1", "pP1", 1.11, 1.11, 1),
            SearchMovie(2, "Title2", "Overview2", "pP2", 2.22, 2.22, 2),
            SearchMovie(3, "Title3", "Overview3", "pP3", 3.33, 3.33, 3)
        )

        assertThat(searchMovieDtoList.toSearchMovies())
            .hasSize(3)
            .isEqualTo(expectedSearchMovieList)
    }

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

        assertThat(searchMovieDto.toSearchMovie()).isEqualTo(expectedSearchMovie)
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

        assertThat(searchMovieDto.toSearchMovie()).isEqualTo(expectedSearchMovie)
    }
}

package com.github.alunicus.cinemalist

import com.github.alunicus.cinemalist.data.Movie
import com.github.alunicus.cinemalist.data.SearchMovie
import com.github.alunicus.cinemalist.data.SearchResult
import com.github.alunicus.cinemalist.data.dto.MovieDto
import com.github.alunicus.cinemalist.data.dto.SearchMovieDto
import com.github.alunicus.cinemalist.data.dto.SearchResultsDto
import com.github.alunicus.cinemalist.extensions.toMovie
import com.github.alunicus.cinemalist.extensions.toSearchMovie
import com.github.alunicus.cinemalist.extensions.toSearchMovies
import com.github.alunicus.cinemalist.extensions.toSearchResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DtoExtensionsTest {
    private val resourceLoader = ResourceLoader()

    private val searchMovieDtoList = listOf(
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

    private val expectedSearchMovieList = listOf(
        SearchMovie(1, "Title1", "Overview1", "pP1", 1.11, 1.11, 1),
        SearchMovie(2, "Title2", "Overview2", "pP2", 2.22, 2.22, 2),
        SearchMovie(3, "Title3", "Overview3", "pP3", 3.33, 3.33, 3)
    )

    @Nested
    inner class MovieTests {
        @Test
        fun `should return Movie from movieDto without null fields`() {
            val expectedMovie = getExpectedMovie(
                "http://marvel.com/avengers_movie/",
                "When an unexpected enemy emerges and threatens global safety and security...",
                "/cezWGskPY5x7GaglTTRN4Fugfb8.jpg",
                143
            )

            val actualResult = resourceLoader.readFromJson<MovieDto>("movie_dto.json").toMovie()

            assertThat(actualResult).isEqualTo(expectedMovie)
        }

        @Test
        fun `should return Movie from movieDto with all possible null fields`() {
            val movieDto = resourceLoader.readFromJson<MovieDto>("movie_dto_with_nulls.json")

            assertThat(movieDto.toMovie()).isEqualTo(getExpectedMovie())
        }

        private fun getExpectedMovie(
            homepage: String = "",
            overview: String = "",
            posterPath: String = "",
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
                "2012-04-25",
                1519557910,
                runtime,
                "Released",
                false,
                7.7,
                21427
            )
        }
    }

    @Nested
    inner class SearchResultTests {
        @Test
        fun `should return SearchResult from SearchResultDto`() {
            val searchResultsDto = SearchResultsDto(1, 1, 3, searchMovieDtoList)

            val expectedSearchResult = SearchResult(3, expectedSearchMovieList)

            assertThat(searchResultsDto.toSearchResult()).isEqualTo(expectedSearchResult)
        }
    }

    @Nested
    inner class SearchMovieTests {
        @Test
        fun `should return list of SearchMovies from a list of SearchMovieDto`() {
            assertThat(searchMovieDtoList.toSearchMovies())
                .hasSize(3)
                .containsExactlyElementsOf(expectedSearchMovieList)
        }

        @Test
        fun `should return SearchMovie converted from SearchMovieDto without null fields`() {
            val searchMovieDto = getSearchMovieDto(
                "/hbn46fQaRmlpBuUrEiFqv0GDL6Y.jpg",
                "/hbn46fQaRmlpBuUrEiFqv0GDL6Y.jpg"
            )

            val expectedSearchMovie = getExpectedSearchMovie("/hbn46fQaRmlpBuUrEiFqv0GDL6Y.jpg")

            assertThat(searchMovieDto.toSearchMovie()).isEqualTo(expectedSearchMovie)
        }

        @Test
        fun `should return SearchMovie converted from SearchMovieDto with all possible null fields`() {
            val searchMovieDto = getSearchMovieDto()

            assertThat(searchMovieDto.toSearchMovie()).isEqualTo(getExpectedSearchMovie())
        }

        private fun getExpectedSearchMovie(posterPath: String = ""): SearchMovie = SearchMovie(
            24428,
            "The Avengers",
            "When an unexpected enemy emerges and threatens global safety and security...",
            posterPath,
            7.353212,
            7.33,
            8503
        )
    }
}

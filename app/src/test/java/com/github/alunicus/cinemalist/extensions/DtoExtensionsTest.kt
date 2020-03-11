package com.github.alunicus.cinemalist.extensions

import com.github.alunicus.cinemalist.ResourceLoader
import com.github.alunicus.cinemalist.data.Movie
import com.github.alunicus.cinemalist.data.SearchMovie
import com.github.alunicus.cinemalist.data.SearchResult
import com.github.alunicus.cinemalist.data.dto.MovieDto
import com.github.alunicus.cinemalist.data.dto.SearchResultsDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*

class DtoExtensionsTest {
    private val resourceLoader = ResourceLoader("./extensions/dto/")

    @Nested
    inner class MovieTests {
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
        fun `should return Movie from movieDto with all possible null fields`() {
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

    @Nested
    inner class SearchResultTests {
        @Test
        fun `should return SearchResult from SearchResultDto`() {
            val expectedSearchMovieList = listOf(
                SearchMovie(
                    24428,
                    "The Avengers",
                    "When an unexpected enemy...",
                    "/cezWGskPY5x7GaglTTRN4Fugfb8.jpg",
                    37.707,
                    7.7,
                    21428
                ),
                SearchMovie(
                    299536,
                    "Avengers: Infinity War",
                    "As the Avengers and...",
                    "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                    65.374,
                    8.3,
                    16635
                ),
                SearchMovie(
                    299534,
                    "Avengers: Endgame",
                    "After the devastating...",
                    "/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
                    38.78,
                    8.3,
                    11108
                )
            )

            val searchResultsDto =
                resourceLoader.readFromJson<SearchResultsDto>("search_movie_result_dto.json")

            val expectedSearchResult = SearchResult(3, expectedSearchMovieList)

            assertThat(searchResultsDto.toSearchResult()).isEqualTo(expectedSearchResult)
        }

        @Test
        fun `should correctly handle all possible nulls during the mapping`() {
            val expectedSearchMovieList = listOf(
                SearchMovie(
                    24428,
                    "The Avengers",
                    "When an unexpected enemy...",
                    "",
                    37.707,
                    7.7,
                    21428
                ),
                SearchMovie(
                    299536,
                    "Avengers: Infinity War",
                    "As the Avengers and...",
                    "",
                    65.374,
                    8.3,
                    16635
                ),
                SearchMovie(
                    299534,
                    "Avengers: Endgame",
                    "After the devastating...",
                    "",
                    38.78,
                    8.3,
                    11108
                )
            )

            val searchResultsDto =
                resourceLoader.readFromJson<SearchResultsDto>("search_movie_result_dto_with_nulls.json")

            val expectedSearchResult = SearchResult(3, expectedSearchMovieList)

            assertThat(searchResultsDto.toSearchResult()).isEqualTo(expectedSearchResult)
        }
    }
}

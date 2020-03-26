package com.github.alunicus.cinemalist.feature.search.domain.model

import com.github.alunicus.cinemalist.ResourceLoader
import com.github.alunicus.cinemalist.feature.search.domain.model.dto.SearchResultsDto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class SearchDtoExtensionsTest {
    private val resourceLoader = ResourceLoader("./feature/search/dto/")

    @Test
    fun `should return SearchResult from SearchResultDto without null fields`() {
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

        Assertions.assertThat(searchResultsDto.toSearchResult()).isEqualTo(expectedSearchResult)
    }

    @Test
    fun `should return SearchResult from SearchResultDto with correctly handled null fields`() {
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

        Assertions.assertThat(searchResultsDto.toSearchResult()).isEqualTo(expectedSearchResult)
    }
}
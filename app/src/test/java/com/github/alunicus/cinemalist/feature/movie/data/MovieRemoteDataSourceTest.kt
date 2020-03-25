package com.github.alunicus.cinemalist.feature.movie.data

import com.github.alunicus.cinemalist.BuildConfig
import com.github.alunicus.cinemalist.ResourceLoader
import com.github.alunicus.cinemalist.feature.movie.domain.model.Cast
import com.github.alunicus.cinemalist.feature.movie.domain.model.Movie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovieRemoteDataSourceTest {
    private val resourceLoader = ResourceLoader("./feature/movie/")

    private val movieApi: MovieApi = mockk()

    private val remoteDataSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(movieApi)

    @Test
    fun `should return Movie object converted from MovieDto`() {
        runBlocking {
            coEvery { movieApi.getMovieById(1, BuildConfig.API_KEY) }
                .returns(resourceLoader.readFromJson("movie.json"))

            val actualResult = remoteDataSource.getMovieById(1)

            assertThat(actualResult)
                .isInstanceOf(Movie::class.java)
        }
    }

    @Test
    fun `should return Cast list converted from MovieCredits`() {
        runBlocking {
            coEvery { movieApi.getMovieCredits(1, BuildConfig.API_KEY) }
                .returns(resourceLoader.readFromJson("credits.json"))

            val actualResult = remoteDataSource.getMovieCredits(1)

            assertThat(actualResult)
                .isInstanceOf(List::class.java)
                .hasOnlyElementsOfType(Cast::class.java)
                .hasSize(7)
        }
    }
}
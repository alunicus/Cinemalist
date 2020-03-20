package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.BuildConfig
import com.github.alunicus.cinemalist.ResourceLoader
import com.github.alunicus.cinemalist.core.MovieNetwork
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.data.Movie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.koin.test.KoinTest

class MovieRepositoryTest : KoinTest {

    private val resourceLoader = ResourceLoader("./feature/movie/")

    private val network: MovieNetwork = mockk()

    private val repository: MovieRepository = MovieRepositoryImpl(network)

    @Nested
    inner class GetMovieByIdTest {
        @Test
        fun `should return success object if request is fine`() {
            runBlocking {
                coEvery { network.api.getMovieById(1, BuildConfig.API_KEY) }
                    .returns(resourceLoader.readFromJson("movie.json"))

                val actualResult = repository.getMovieById(1)

                assertThat(actualResult).isInstanceOf(Result.Success::class.java)
            }
        }

        @Test
        fun `should return mapped instance of Movie in case of success`() {
            coEvery { network.api.getMovieById(1, BuildConfig.API_KEY) }
                .returns(resourceLoader.readFromJson("movie.json"))

            runBlocking {
                val actualResult = (repository.getMovieById(1) as Result.Success).result

                assertThat(actualResult).isInstanceOf(Movie::class.java)
                assertThat(actualResult.id).isEqualTo(458156)
                assertThat(actualResult.originalTitle).isEqualTo("John Wick: Chapter 3 - Parabellum")
            }
        }

        @Test
        fun `should return server error in case of failure`() {
            coEvery { network.api.getMovieById(1, BuildConfig.API_KEY) }
                .throws(Exception())

            runBlocking {
                val actualResult = repository.getMovieById(1)

                assertThat(actualResult).isInstanceOf(Result.Failure::class.java)
            }
        }
    }

    @Nested
    inner class GetMovieCastTest {
        @Test
        fun `should return cast by movie id`() {

        }

        @Test
        fun `should return server error for a movie cast`() {

        }
    }
}
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

                assertThat(actualResult)
                    .isInstanceOf(Result.Success::class.java)
            }
        }

        @Test
        fun `should return mapped instance of Movie in case of success`() {
            coEvery { network.api.getMovieById(1, BuildConfig.API_KEY) }
                .returns(resourceLoader.readFromJson("movie.json"))

            runBlocking {
                val actualResult = (repository.getMovieById(1) as Result.Success).result

                assertThat(actualResult)
                    .isInstanceOf(Movie::class.java)
            }
        }

        @Test
        fun `should return server error in case of failure`() {
            coEvery { network.api.getMovieById(1, BuildConfig.API_KEY) }
                .throws(Exception())

            runBlocking {
                val actualResult = repository.getMovieById(1)

                assertThat(actualResult)
                    .isInstanceOf(Result.Failure::class.java)
            }
        }
    }

    @Nested
    inner class GetMovieCastTest {
        @Test
        fun `should return success object if request is fine`() {
            coEvery { network.api.getMovieCredits(1, BuildConfig.API_KEY) }
                .returns(resourceLoader.readFromJson("credits.json"))

            runBlocking {
                val actualResult = repository.getMovieCast(1)

                assertThat(actualResult)
                    .isInstanceOf(Result.Success::class.java)
            }
        }

        @Test
        fun `should return mapped instance of Cast by movie id in case of success`() {
            coEvery { network.api.getMovieCredits(1, BuildConfig.API_KEY) }
                .returns(resourceLoader.readFromJson("credits.json"))

            runBlocking {
                val actualResult = (repository.getMovieCast(1) as Result.Success).result

                assertThat(actualResult)
                    .isInstanceOf(List::class.java)
                    .hasSize(7)
            }
        }

        @Test
        fun `should return server error in case of failure`() {
            coEvery { network.api.getMovieCredits(1, BuildConfig.API_KEY) }
                .throws(Exception())

            runBlocking {
                val actualResult = repository.getMovieCast(1)

                assertThat(actualResult)
                    .isInstanceOf(Result.Failure::class.java)
            }
        }
    }
}
package com.github.alunicus.cinemalist.feature.movie.data

import com.github.alunicus.cinemalist.core.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.koin.test.KoinTest

class MovieRepositoryTest : KoinTest {
    private val movieRemoteDataSource: MovieRemoteDataSource = mockk()

    private val repository: MovieRepository = MovieRepositoryImpl(movieRemoteDataSource)

    @Nested
    inner class GetMovieDetailsByIdTest {
        @Test
        fun `should return success object if request is fine`() {
            runBlocking {
                coEvery { movieRemoteDataSource.getMovieDetailsById(1) }
                    .returns(mockk())

                val actualResult = repository.getMovieDetailsById(1)

                assertThat(actualResult)
                    .isInstanceOf(Result.Success::class.java)
            }
        }

        @Test
        fun `should return server error in case of failure`() {
            coEvery { movieRemoteDataSource.getMovieDetailsById(1) }
                .throws(Exception())

            runBlocking {
                val actualResult = repository.getMovieDetailsById(1)

                assertThat(actualResult)
                    .isInstanceOf(Result.Failure::class.java)
            }
        }
    }

    @Nested
    inner class GetMovieCastTest {
        @Test
        fun `should return success object if request is fine`() {
            coEvery { movieRemoteDataSource.getMovieCredits(1) }
                .returns(mockk())

            runBlocking {
                val actualResult = repository.getMovieCast(1)

                assertThat(actualResult)
                    .isInstanceOf(Result.Success::class.java)
            }
        }

        @Test
        fun `should return server error in case of failure`() {
            coEvery { movieRemoteDataSource.getMovieCredits(1) }
                .throws(Exception())

            runBlocking {
                val actualResult = repository.getMovieCast(1)

                assertThat(actualResult)
                    .isInstanceOf(Result.Failure::class.java)
            }
        }
    }
}
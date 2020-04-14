package com.github.alunicus.cinemalist.feature.movie.data

import com.github.alunicus.cinemalist.core.ConnectionManager
import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.feature.movie.domain.getTestCastList
import com.github.alunicus.cinemalist.feature.movie.domain.getTestMovieDetails
import com.github.alunicus.cinemalist.feature.movie.domain.model.Cast
import com.github.alunicus.cinemalist.feature.movie.domain.model.MovieDetails
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MovieRepositoryTest {
    private val movieRemoteDataSource: MovieRemoteDataSource = mockk()
    private val connectionManager: ConnectionManager = mockk()

    private val testMovieId = 458156
    
    private val repository: MovieRepository =
        MovieRepositoryImpl(movieRemoteDataSource, connectionManager)

    @Nested
    inner class GetMovieDetailsByIdTest {
        @Test
        fun `should return success object if request is fine`() {
            runBlocking {
                coEvery { movieRemoteDataSource.getMovieDetailsById(testMovieId) }
                    .returns(mockk())

                every { connectionManager.isConnected() }.returns(true)

                val actualResult = repository.getMovieDetailsById(testMovieId)

                assertThat(actualResult)
                    .isInstanceOf(Result.Success::class.java)
            }
        }

        @Test
        fun `should return correct response from the data source`() {
            runBlocking {
                val testMovieDetails = getTestMovieDetails()

                coEvery { movieRemoteDataSource.getMovieDetailsById(testMovieId) }
                    .returns(testMovieDetails)

                every { connectionManager.isConnected() }.returns(true)

                val actualResult =
                    (repository.getMovieDetailsById(testMovieId) as Result.Success<MovieDetails>).result

                assertThat(actualResult).isEqualTo(testMovieDetails)
            }
        }

        @Test
        fun `should return server error in case of failure`() {
            coEvery { movieRemoteDataSource.getMovieDetailsById(testMovieId) }
                .throws(Exception())

            every { connectionManager.isConnected() }.returns(true)

            runBlocking {
                val actualResult = (repository.getMovieDetailsById(testMovieId) as Result.Failure).error

                assertThat(actualResult)
                    .isInstanceOf(Error.ServerError::class.java)
            }
        }

        @Test
        fun `should return connection error if no internet connection`() {
            every { connectionManager.isConnected() }.returns(false)

            runBlocking {
                val actualResult = (repository.getMovieDetailsById(testMovieId) as Result.Failure).error

                assertThat(actualResult)
                    .isInstanceOf(Error.NetworkConnectionError::class.java)
            }
        }
    }

    @Nested
    inner class GetMovieCastTest {
        @Test
        fun `should return success object if request is fine`() {
            coEvery { movieRemoteDataSource.getMovieCredits(testMovieId) }
                .returns(mockk())

            every { connectionManager.isConnected() }.returns(true)

            runBlocking {
                val actualResult = repository.getMovieCast(testMovieId)

                assertThat(actualResult)
                    .isInstanceOf(Result.Success::class.java)
            }
        }

        @Test
        fun `should return correct response from the data source`() {
            runBlocking {
                val testCastList = getTestCastList()

                coEvery { movieRemoteDataSource.getMovieCredits(testMovieId) }
                    .returns(testCastList)

                every { connectionManager.isConnected() }.returns(true)

                val actualResult =
                    (repository.getMovieCast(testMovieId) as Result.Success<List<Cast>>).result

                assertThat(actualResult).isEqualTo(testCastList)
            }
        }

        @Test
        fun `should return server error in case of failure`() {
            coEvery { movieRemoteDataSource.getMovieCredits(testMovieId) }
                .throws(Exception())

            every { connectionManager.isConnected() }.returns(true)

            runBlocking {
                val actualResult = repository.getMovieCast(testMovieId)

                assertThat(actualResult)
                    .isInstanceOf(Result.Failure::class.java)
            }
        }

        @Test
        fun `should return connection error if no internet connection`() {
            every { connectionManager.isConnected() }.returns(false)

            runBlocking {
                val actualResult = (repository.getMovieCast(testMovieId) as Result.Failure).error

                assertThat(actualResult)
                    .isInstanceOf(Error.NetworkConnectionError::class.java)
            }
        }
    }
}
package com.github.alunicus.cinemalist.feature.movie.domain

import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result.Failure
import com.github.alunicus.cinemalist.core.Result.Success
import com.github.alunicus.cinemalist.feature.movie.data.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GetMovieUseCaseTest {

    private val movieRepository: MovieRepository = mockk()

    private val useCase = GetMovieUseCase(movieRepository)

    private val testMovieId = 458156

    @Test
    fun `should return Movie if getting of movie details and cast are successful`() {
        val testMovieDetails = getTestMovieDetails()
        val testCastList = getTestCastList()

        coEvery { movieRepository.getMovieDetailsById(testMovieId) }
            .returns(Success(testMovieDetails))

        coEvery { movieRepository.getMovieCast(testMovieId) }.returns(Success(testCastList))

        runBlocking {
            val result = (useCase.getMovie(testMovieId) as Success).result

            assertThat(result.movieDetails).isEqualTo(testMovieDetails)
            assertThat(result.cast).isEqualTo(testCastList)
        }
    }

    @Test
    fun `should return Movie with cast empty list if getting of cast failed`() {
        val testMovieDetails = getTestMovieDetails()

        coEvery { movieRepository.getMovieDetailsById(testMovieId) }
            .returns(Success(testMovieDetails))

        coEvery { movieRepository.getMovieCast(testMovieId) }.returns(Failure(Error.ServerError))

        runBlocking {
            val result = (useCase.getMovie(testMovieId) as Success).result

            assertThat(result.movieDetails).isEqualTo(testMovieDetails)
            assertThat(result.cast).isEmpty()
        }
    }

    @Test
    fun `should return Failure from failed movie details request`() {
        val testMovieFailure = Failure(Error.ServerError)

        coEvery { movieRepository.getMovieDetailsById(testMovieId) }.returns(testMovieFailure)

        runBlocking {
            assertThat(useCase.getMovie(testMovieId)).isEqualTo(testMovieFailure)
        }
    }

    @Test
    fun `should not call getMovieCast if movie details failed`() {
        val testMovieFailure = Failure(Error.ServerError)

        coEvery { movieRepository.getMovieDetailsById(testMovieId) }.returns(testMovieFailure)

        coVerify(exactly = 0) { movieRepository.getMovieCast(testMovieId) }
    }
}
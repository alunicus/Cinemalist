package com.github.alunicus.cinemalist.feature.movie.domain

import com.github.alunicus.cinemalist.core.Result.Success
import com.github.alunicus.cinemalist.feature.movie.data.MovieRepository
import com.github.alunicus.cinemalist.feature.movie.domain.model.Movie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GetMovieUseCaseTest {

    private val movieRepository: MovieRepository = mockk()

    private val useCase = GetMovieUseCase(movieRepository)

    @Test
    fun `should return Movie if getting of movie details and cast are successful`() {
        coEvery { movieRepository.getMovieDetailsById(1) }.returns(Success(mockk()))
        coEvery { movieRepository.getMovieCast(1) }.returns(Success(listOf(mockk(), mockk())))

        runBlocking {
            val result = (useCase.getMovie(1) as Success).result

            assertThat(result).isInstanceOf(Movie::class.java)
            assertThat(result.cast).hasSize(2)
        }
    }
}
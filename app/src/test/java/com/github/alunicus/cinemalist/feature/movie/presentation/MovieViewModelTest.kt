package com.github.alunicus.cinemalist.feature.movie.presentation

import com.github.alunicus.cinemalist.CoroutinesTestExtension
import com.github.alunicus.cinemalist.InstantExecutorExtension
import com.github.alunicus.cinemalist.R
import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.core.Result.Success
import com.github.alunicus.cinemalist.feature.movie.domain.GetMovieUseCase
import com.github.alunicus.cinemalist.feature.movie.domain.getTestCastList
import com.github.alunicus.cinemalist.feature.movie.domain.getTestMovieDetails
import com.github.alunicus.cinemalist.feature.movie.domain.model.Movie
import com.github.alunicus.cinemalist.observeForTesting
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class, CoroutinesTestExtension::class)
class MovieViewModelTest {
    private val movieUseCase: GetMovieUseCase = mockk()

    private val viewModel = MovieViewModel(movieUseCase)

    private val testMovieId = 458156

    @Test
    fun `Should return correct Movie object when result is a Success`() {
        val testMovie = Movie(getTestMovieDetails(), getTestCastList())

        coEvery { movieUseCase.getMovie(testMovieId) }.returns(Success(testMovie))

        viewModel.loadMovie(testMovieId)

        viewModel.onMovieLoaded().observeForTesting {
            assertThat(viewModel.onMovieLoaded().value).isEqualTo(testMovie)
            assertThat(viewModel.onError().value).isNull()
        }
    }

    @Test
    fun `Should return server error when result is a ServerError`() {
        coEvery { movieUseCase.getMovie(testMovieId) }.returns(Result.Failure(Error.ServerError))

        viewModel.loadMovie(testMovieId)

        viewModel.onMovieLoaded().observeForTesting {
            assertThat(viewModel.onError().value).isEqualTo(R.string.server_error)
            assertThat(viewModel.onMovieLoaded().value).isNull()
        }
    }

    @Test
    fun `Should return server error when result is a NetworkConnectionError`() {
        coEvery { movieUseCase.getMovie(testMovieId) }.returns(Result.Failure(Error.NetworkConnectionError))

        viewModel.loadMovie(testMovieId)

        viewModel.onMovieLoaded().observeForTesting {
            assertThat(viewModel.onError().value).isEqualTo(R.string.network_connection_error)
            assertThat(viewModel.onMovieLoaded().value).isNull()
        }
    }
}

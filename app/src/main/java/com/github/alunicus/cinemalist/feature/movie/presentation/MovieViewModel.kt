package com.github.alunicus.cinemalist.feature.movie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.feature.movie.domain.GetMovieCastUseCase
import com.github.alunicus.cinemalist.feature.movie.domain.GetMovieUseCase
import com.github.alunicus.cinemalist.feature.movie.domain.model.Cast
import com.github.alunicus.cinemalist.feature.movie.domain.model.Movie
import com.github.alunicus.cinemalist.feature.movie.domain.model.MovieDetails
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieUseCase: GetMovieUseCase,
    private val castUseCase: GetMovieCastUseCase
) : ViewModel() {

    private val movieLoaded by lazy { MutableLiveData<Movie>() }

    fun onMovieLoaded(): LiveData<Movie> = movieLoaded

    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            when (val result = movieUseCase.getMovieDetails(movieId)) {
                is Result.Success -> movieLoaded.value =
                    getMovie(result.result, castUseCase.getMovieCast(movieId))
                is Result.Failure -> println("Error:")
            }
        }
    }

    private fun getMovie(movieDetails: MovieDetails, castResult: Result<List<Cast>, Error>): Movie {
        return when (castResult) {
            is Result.Success -> Movie(
                movieDetails,
                castResult.result
            )
            is Result.Failure -> Movie(
                movieDetails,
                emptyList()
            )
        }
    }
}
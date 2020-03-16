package com.github.alunicus.cinemalist.feature.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.data.Cast
import com.github.alunicus.cinemalist.data.Movie
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieUseCase: GetMovieUseCase,
    private val castUseCase: GetMovieCastUseCase
) : ViewModel() {

    private val movieLoaded by lazy { MutableLiveData<FullMovie>() }

    fun onMovieLoaded(): LiveData<FullMovie> = movieLoaded

    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            when (val result = movieUseCase.getMovie(movieId)) {
                is Result.Success -> movieLoaded.value =
                    getFullMovie(result.result, castUseCase.getMovieCast(movieId))
                is Result.Failure -> println("Error:")
            }
        }
    }

    private fun getFullMovie(movie: Movie, castResult: Result<List<Cast>, Error>): FullMovie {
        return when (castResult) {
            is Result.Success -> FullMovie(movie, castResult.result)
            is Result.Failure -> FullMovie(movie, emptyList())
        }
    }
}
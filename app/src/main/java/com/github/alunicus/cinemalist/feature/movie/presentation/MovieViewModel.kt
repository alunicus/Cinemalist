package com.github.alunicus.cinemalist.feature.movie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.extensions.handleFailure
import com.github.alunicus.cinemalist.feature.movie.domain.GetMovieUseCase
import com.github.alunicus.cinemalist.feature.movie.domain.model.Movie
import kotlinx.coroutines.launch

class MovieViewModel(private val movieUseCase: GetMovieUseCase) : ViewModel() {

    private val movieLoaded by lazy { MutableLiveData<Movie>() }
    private val error by lazy { MutableLiveData<Int>() }

    fun onMovieLoaded(): LiveData<Movie> = movieLoaded
    fun onError(): LiveData<Int> = error

    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            when (val result = movieUseCase.getMovie(movieId)) {
                is Result.Success -> movieLoaded.value = result.result
                is Result.Failure -> error.value = result.handleFailure().messageResId
            }
        }
    }
}

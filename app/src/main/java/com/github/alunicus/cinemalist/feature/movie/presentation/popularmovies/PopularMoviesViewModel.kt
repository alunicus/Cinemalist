package com.github.alunicus.cinemalist.feature.movie.presentation.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.extensions.handleFailure
import com.github.alunicus.cinemalist.feature.movie.domain.GetPopularMoviesUseCase
import com.github.alunicus.cinemalist.feature.movie.domain.model.PopularMovie
import kotlinx.coroutines.launch

class PopularMoviesViewModel(private val popularMoviesUseCase: GetPopularMoviesUseCase) :
    ViewModel() {
    private val popularMoviesLoaded by lazy { MutableLiveData<List<PopularMovie>>() }
    private val error by lazy { MutableLiveData<Int>() }
    private val progressVisible by lazy { MutableLiveData<Boolean>() }

    fun onPopularMoviesLoaded(): LiveData<List<PopularMovie>> = popularMoviesLoaded
    fun onError(): LiveData<Int> = error
    fun onProgressVisibilityChanged(): LiveData<Boolean> = progressVisible

    fun loadPopularMovies() {
        viewModelScope.launch {
            progressVisible.value = true

            when (val result = popularMoviesUseCase.getPopularMovies()) {
                is Result.Success -> popularMoviesLoaded.value = result.result
                is Result.Failure -> error.value = result.handleFailure()
            }

            progressVisible.value = false
        }
    }
}

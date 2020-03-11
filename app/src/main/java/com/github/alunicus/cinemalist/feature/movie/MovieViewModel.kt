package com.github.alunicus.cinemalist.feature.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.data.Movie
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val movieLoaded by lazy { MutableLiveData<Movie>() }

    fun onMovieLoaded(): LiveData<Movie> = movieLoaded

    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            when (val result = GetMovieUseCase(repository, movieId).getMovie()) {
                is Result.Success -> movieLoaded.value = result.result
                is Result.Failure -> println("Error:")
            }
        }
    }
}
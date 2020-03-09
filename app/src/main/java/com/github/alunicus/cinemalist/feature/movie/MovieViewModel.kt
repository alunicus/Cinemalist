package com.github.alunicus.cinemalist.feature.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.alunicus.cinemalist.data.Movie
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private val movieLoaded by lazy { MutableLiveData<Movie>() }

    public fun onMovieLoaded(): LiveData<Movie> = movieLoaded

    public fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            movieLoaded.value = repository.getMovieById(movieId)
        }
    }
}
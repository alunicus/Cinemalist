package com.github.alunicus.cinemalist.feature.movie.presentation.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.alunicus.cinemalist.feature.movie.data.MovieRepository
import com.github.alunicus.cinemalist.feature.movie.data.PopularMoviesDataSource
import com.github.alunicus.cinemalist.feature.movie.domain.model.PopularMovie

class PopularMoviesViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val pagedPopularMovies: LiveData<PagedList<PopularMovie>>

    init {
        val dataSourceFactory = object : DataSource.Factory<Int, PopularMovie>() {
            override fun create(): DataSource<Int, PopularMovie> {
                return PopularMoviesDataSource(viewModelScope, movieRepository)
            }
        }

        pagedPopularMovies = LivePagedListBuilder(dataSourceFactory, 10).build()
    }

    fun onPopularMoviesLoaded(): LiveData<PagedList<PopularMovie>> = pagedPopularMovies
}

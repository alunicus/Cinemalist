package com.github.alunicus.cinemalist.feature.movie.data

import androidx.paging.PageKeyedDataSource
import com.github.alunicus.cinemalist.core.Result.Failure
import com.github.alunicus.cinemalist.core.Result.Success
import com.github.alunicus.cinemalist.feature.movie.domain.model.PopularMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

private const val INITIAL_PAGE = 1

class PopularMoviesDataSource(
    private val scope: CoroutineScope,
    private val repository: MovieRepository
) :
    PageKeyedDataSource<Int, PopularMovie>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PopularMovie>
    ) {
        scope.launch {
            when (val response = repository.getPopularMovies(INITIAL_PAGE)) {
                is Success -> callback.onResult(
                    response.result.movies,
                    null,
                    response.result.pageNumber + 1
                )
                is Failure -> callback.onResult(listOf(), null, INITIAL_PAGE)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PopularMovie>) {
        scope.launch {
            when (val response = repository.getPopularMovies(params.key)) {
                is Success -> callback.onResult(
                    response.result.movies,
                    response.result.pageNumber + 1
                )
                is Failure -> callback.onResult(listOf(), params.key)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PopularMovie>) {
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}
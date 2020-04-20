package com.github.alunicus.cinemalist.feature.movie.data

import com.github.alunicus.cinemalist.core.ConnectionManager
import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.feature.movie.domain.model.Cast
import com.github.alunicus.cinemalist.feature.movie.domain.model.MovieDetails
import com.github.alunicus.cinemalist.feature.movie.domain.model.PopularMoviePage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val connectionManager: ConnectionManager
) :
    MovieRepository {
    override suspend fun getMovieDetailsById(id: Int): Result<MovieDetails, Error> {
        return request { remoteDataSource.getMovieDetailsById(id) }
    }

    override suspend fun getMovieCast(id: Int): Result<List<Cast>, Error> {
        return request { remoteDataSource.getMovieCredits(id) }
    }

    override suspend fun getPopularMovies(pageNumber: Int): Result<PopularMoviePage, Error> {
        return request { remoteDataSource.getPopularMovies(pageNumber) }
    }

    private suspend fun <T> request(call: suspend () -> T): Result<T, Error> {
        return withContext(Dispatchers.IO) {
            return@withContext if (connectionManager.isConnected()) {
                try {
                    Result.Success(call.invoke())
                } catch (e: Exception) {
                    e.printStackTrace()
                    Result.Failure(Error.ServerError)
                }
            } else {
                Result.Failure(Error.NetworkConnectionError)
            }
        }
    }
}

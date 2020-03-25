package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.feature.movie.data.MovieRemoteDataSourceImpl
import com.github.alunicus.cinemalist.feature.movie.model.Cast
import com.github.alunicus.cinemalist.feature.movie.model.Movie

class MovieRepositoryImpl(private val remoteDataSource: MovieRemoteDataSourceImpl) : MovieRepository {
    override suspend fun getMovieById(id: Int): Result<Movie, Error> {
        return request { remoteDataSource.getMovieById(id) }
    }

    override suspend fun getMovieCast(id: Int): Result<List<Cast>, Error> {
        return request { remoteDataSource.getMovieCredits(id) }
    }

    private suspend fun <T> request(call: suspend () -> T): Result<T, Error> {
        return try {
            Result.Success(call.invoke())
        } catch (e: Exception) {
            Result.Failure(Error.ServerError)
        }
    }
}

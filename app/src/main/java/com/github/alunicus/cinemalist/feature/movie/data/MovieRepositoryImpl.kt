package com.github.alunicus.cinemalist.feature.movie.data

import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.feature.movie.domain.model.Cast
import com.github.alunicus.cinemalist.feature.movie.domain.model.MovieDetails

class MovieRepositoryImpl(private val remoteDataSource: MovieRemoteDataSource) :
    MovieRepository {
    override suspend fun getMovieDetailsById(id: Int): Result<MovieDetails, Error> {
        return request { remoteDataSource.getMovieDetailsById(id) }
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

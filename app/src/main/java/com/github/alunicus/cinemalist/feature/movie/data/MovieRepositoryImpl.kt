package com.github.alunicus.cinemalist.feature.movie.data

import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.feature.movie.domain.model.Cast
import com.github.alunicus.cinemalist.feature.movie.domain.model.MovieDetails
import com.github.alunicus.cinemalist.feature.movie.domain.model.PopularMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(private val remoteDataSource: MovieRemoteDataSource) :
    MovieRepository {
    override suspend fun getMovieDetailsById(id: Int): Result<MovieDetails, Error> {
        return request { remoteDataSource.getMovieDetailsById(id) }
    }

    override suspend fun getMovieCast(id: Int): Result<List<Cast>, Error> {
        return request { remoteDataSource.getMovieCredits(id) }
    }

    override suspend fun getPopularMovies(): Result<List<PopularMovie>, Error> {
        return request { remoteDataSource.getPopularMovies() }
    }

    private suspend fun <T> request(call: suspend () -> T): Result<T, Error> {
        return withContext(Dispatchers.IO) {
            try {
                Result.Success(call.invoke())
            } catch (e: Exception) {
                Result.Failure(Error.ServerError)
            }
        }
    }
}

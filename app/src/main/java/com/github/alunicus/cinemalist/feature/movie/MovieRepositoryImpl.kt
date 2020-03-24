package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.BuildConfig
import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.MovieNetwork
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.extensions.toCast
import com.github.alunicus.cinemalist.extensions.toMovie
import com.github.alunicus.cinemalist.feature.movie.model.Cast
import com.github.alunicus.cinemalist.feature.movie.model.Movie

class MovieRepositoryImpl(private val network: MovieNetwork) : MovieRepository {
    override suspend fun getMovieById(id: Int): Result<Movie, Error> {
        return request { network.api.getMovieById(id, BuildConfig.API_KEY).toMovie() }
    }

    override suspend fun getMovieCast(id: Int): Result<List<Cast>, Error> {
        return request { network.api.getMovieCredits(id, BuildConfig.API_KEY).toCast() }
    }

    private suspend fun <T> request(call: suspend () -> T): Result<T, Error> {
        return try {
            Result.Success(call.invoke())
        } catch (e: Exception) {
            Result.Failure(Error.ServerError)
        }
    }
}

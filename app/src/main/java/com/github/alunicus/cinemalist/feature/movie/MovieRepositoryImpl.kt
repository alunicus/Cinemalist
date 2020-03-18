package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.BuildConfig
import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Network
import com.github.alunicus.cinemalist.core.Result
import com.github.alunicus.cinemalist.data.Cast
import com.github.alunicus.cinemalist.data.Movie
import com.github.alunicus.cinemalist.extensions.toCast
import com.github.alunicus.cinemalist.extensions.toMovie

class MovieRepositoryImpl(network: Network) : MovieRepository {
    private var api: MovieApi = network.createRetrofit(MovieApi::class.java)

    override suspend fun getMovieById(id: Int): Result<Movie, Error> {
        return try {
            Result.Success(api.getMovieById(id, BuildConfig.API_KEY).toMovie())
        } catch (e: Exception) {
            Result.Failure(Error.ServerError)
        }
    }

    override suspend fun getMovieCast(id: Int): Result<List<Cast>, Error> {
        return try {
            Result.Success(api.getMovieCredits(id, BuildConfig.API_KEY).toCast())
        } catch (e: Exception) {
            Result.Failure(Error.ServerError)
        }
    }
}
package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.BuildConfig
import com.github.alunicus.cinemalist.core.Network
import com.github.alunicus.cinemalist.data.Movie
import com.github.alunicus.cinemalist.extensions.toMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(network: Network) : MovieRepository {
    private var api: MovieApi = network.createRetrofit(MovieApi::class.java)

    override suspend fun getMovieById(id: Int): Movie {
        return withContext(Dispatchers.IO) {
            api.getMovieById(id, BuildConfig.API_KEY).toMovie()
        }
    }
}
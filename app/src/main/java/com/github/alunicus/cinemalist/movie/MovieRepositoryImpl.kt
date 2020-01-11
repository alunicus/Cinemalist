package com.github.alunicus.cinemalist.movie

import com.github.alunicus.cinemalist.BuildConfig
import com.github.alunicus.cinemalist.Network
import com.github.alunicus.cinemalist.data.Movie

class MovieRepositoryImpl(network: Network) : MovieRepository {
    private var api: MovieApi = network.createRetrofit(MovieApi::class.java)

    override suspend fun getMovieById(id: Int): Movie {
        return api.getMovieById(id, BuildConfig.API_KEY)
    }
}
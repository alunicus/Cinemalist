package com.github.alunicus.cinemalist.feature.movie.data

import com.github.alunicus.cinemalist.BuildConfig
import com.github.alunicus.cinemalist.extensions.toCast
import com.github.alunicus.cinemalist.extensions.toMovie
import com.github.alunicus.cinemalist.feature.movie.domain.model.Cast
import com.github.alunicus.cinemalist.feature.movie.domain.model.Movie
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRemoteDataSourceImpl : MovieRemoteDataSource {
    private val api: MovieApi by lazy {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd")
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        retrofit.create(MovieApi::class.java)
    }

    override suspend fun getMovieById(movieId: Int): Movie {
        return api.getMovieById(movieId, BuildConfig.API_KEY).toMovie()
    }

    override suspend fun getMovieCredits(movieId: Int): List<Cast> {
        return api.getMovieCredits(movieId, BuildConfig.API_KEY).toCast()
    }
}
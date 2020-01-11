package com.github.alunicus.cinemalist.movie

import com.github.alunicus.cinemalist.data.Movie
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepositoryImpl : MovieRepository {
    private var api: MovieApi

    init {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = retrofit.create(MovieApi::class.java)
    }

    override suspend fun getMovieById(id: Int): Movie {
        return api.getMovieById(id, "")
    }
}
package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.data.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): MovieDto
}
package com.github.alunicus.cinemalist.feature.movie.data

import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.MovieDto
import com.github.alunicus.cinemalist.feature.search.domain.model.dto.MovieCreditsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): MovieDto

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): MovieCreditsDto
}
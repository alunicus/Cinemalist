package com.github.alunicus.cinemalist.feature.movie.data

import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.MovieCreditsDto
import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.MovieDetailsDto
import com.github.alunicus.cinemalist.feature.movie.domain.model.dto.PopularMovieDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetailsById(@Path("movie_id") movieId: Int): MovieDetailsDto

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Int): MovieCreditsDto

    @GET("/movie/popular")
    suspend fun getPopularMovies(): PopularMovieDto
}
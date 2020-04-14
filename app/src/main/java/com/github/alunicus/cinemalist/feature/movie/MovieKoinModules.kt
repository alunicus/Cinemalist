package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.core.ConnectionManager
import com.github.alunicus.cinemalist.core.NetworkInterceptor
import com.github.alunicus.cinemalist.feature.movie.data.*
import com.github.alunicus.cinemalist.feature.movie.domain.GetMovieUseCase
import com.github.alunicus.cinemalist.feature.movie.domain.GetPopularMoviesUseCase
import com.github.alunicus.cinemalist.feature.movie.presentation.MovieViewModel
import com.github.alunicus.cinemalist.feature.movie.presentation.popularmovies.PopularMoviesViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val movieModule = module {
    factory {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd")
            .create()

        val httpClient = OkHttpClient.Builder().addInterceptor(NetworkInterceptor())

        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
            .create(MovieApi::class.java)
    }

    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }

    single<MovieRepository> { MovieRepositoryImpl(get()) }

    single { ConnectionManager(androidContext()) }

    viewModel { MovieViewModel(get()) }

    viewModel { PopularMoviesViewModel(get()) }

    factory { GetMovieUseCase(get()) }

    factory { GetPopularMoviesUseCase(get()) }
}
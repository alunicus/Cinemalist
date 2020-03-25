package com.github.alunicus.cinemalist.feature.movie

import com.github.alunicus.cinemalist.feature.movie.data.*
import com.github.alunicus.cinemalist.feature.movie.domain.GetMovieCastUseCase
import com.github.alunicus.cinemalist.feature.movie.domain.GetMovieUseCase
import com.github.alunicus.cinemalist.feature.movie.presentation.MovieViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
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

        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MovieApi::class.java)
    }

    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }

    single<MovieRepository> { MovieRepositoryImpl(get()) }

    viewModel { MovieViewModel(get(), get()) }

    factory { GetMovieUseCase(get()) }

    factory { GetMovieCastUseCase(get()) }
}
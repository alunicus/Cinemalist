package com.github.alunicus.cinemalist

import com.github.alunicus.cinemalist.core.Network
import com.github.alunicus.cinemalist.feature.movie.GetMovieUseCase
import com.github.alunicus.cinemalist.feature.movie.MovieRepository
import com.github.alunicus.cinemalist.feature.movie.MovieRepositoryImpl
import com.github.alunicus.cinemalist.feature.movie.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single { Network() }
}

val movieModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }

    viewModel { MovieViewModel(get()) }

    factory { GetMovieUseCase(get()) }
}
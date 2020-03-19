package com.github.alunicus.cinemalist

import com.github.alunicus.cinemalist.core.MovieNetwork
import com.github.alunicus.cinemalist.feature.movie.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single { MovieNetwork() }
}

val movieModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }

    viewModel { MovieViewModel(get(), get()) }

    factory { GetMovieUseCase(get()) }

    factory { GetMovieCastUseCase(get()) }
}
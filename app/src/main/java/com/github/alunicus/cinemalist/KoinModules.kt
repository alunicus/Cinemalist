package com.github.alunicus.cinemalist

import com.github.alunicus.cinemalist.core.Network
import com.github.alunicus.cinemalist.feature.movie.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single { Network() }
}

val movieModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }

    viewModel { MovieViewModel(get(), get()) }

    factory { GetMovieUseCase(get()) }

    factory { GetMovieCastUseCase(get()) }
}
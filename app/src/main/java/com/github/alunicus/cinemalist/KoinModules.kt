package com.github.alunicus.cinemalist

import com.github.alunicus.cinemalist.feature.movie.data.MovieRemoteDataSourceImpl
import com.github.alunicus.cinemalist.feature.movie.data.MovieRepository
import com.github.alunicus.cinemalist.feature.movie.data.MovieRepositoryImpl
import com.github.alunicus.cinemalist.feature.movie.domain.GetMovieCastUseCase
import com.github.alunicus.cinemalist.feature.movie.domain.GetMovieUseCase
import com.github.alunicus.cinemalist.feature.movie.presentation.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single { MovieRemoteDataSourceImpl() }
}

val movieModule = module {
    single<MovieRepository> {
        MovieRepositoryImpl(
            get()
        )
    }

    viewModel {
        MovieViewModel(
            get(),
            get()
        )
    }

    factory {
        GetMovieUseCase(
            get()
        )
    }

    factory {
        GetMovieCastUseCase(
            get()
        )
    }
}
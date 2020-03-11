package com.github.alunicus.cinemalist

import android.app.Application
import com.github.alunicus.cinemalist.core.Network
import com.github.alunicus.cinemalist.feature.movie.GetMovieUseCase
import com.github.alunicus.cinemalist.feature.movie.MovieRepository
import com.github.alunicus.cinemalist.feature.movie.MovieRepositoryImpl
import com.github.alunicus.cinemalist.feature.movie.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CinemalistApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CinemalistApplication)
            modules(listOf(modules, movieModule))
        }
    }
}

val modules = module {
    single { Network() }
}

val movieModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }

    viewModel { MovieViewModel(get()) }

    factory { GetMovieUseCase(get()) }
}
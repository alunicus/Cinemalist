package com.github.alunicus.cinemalist

import android.app.Application
import com.github.alunicus.cinemalist.movie.MovieRepository
import com.github.alunicus.cinemalist.movie.MovieRepositoryImpl
import com.github.alunicus.cinemalist.movie.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CinemalistApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CinemalistApplication)
            modules(modules)
        }
    }
}

val modules = module {
    single { Network() }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    viewModel { MovieViewModel(get()) }
}
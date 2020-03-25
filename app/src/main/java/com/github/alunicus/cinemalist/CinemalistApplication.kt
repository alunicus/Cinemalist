package com.github.alunicus.cinemalist

import android.app.Application
import com.github.alunicus.cinemalist.feature.movie.movieModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CinemalistApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CinemalistApplication)
            modules(listOf(movieModule))
        }
    }
}

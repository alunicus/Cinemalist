package com.github.alunicus.cinemalist

import com.github.alunicus.cinemalist.core.ConnectionManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedModule = module {
    single { ConnectionManager(androidContext()) }
}
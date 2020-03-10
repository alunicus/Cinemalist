package com.github.alunicus.cinemalist.core

import kotlinx.coroutines.*

abstract class UseCase<out Type : Any>(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
    abstract suspend fun run(): Result<Type, Error>

    operator fun invoke(scope: CoroutineScope) {
        scope.launch {
            withContext(dispatcher) {
                run()
            }
        }
    }
}
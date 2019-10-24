package com.github.alunicus.cinemalist

sealed class Result<out L, out R> {
    data class Failure<out L>(val error: L) : Result<L, Nothing>()
    data class Success<out R>(val result: R) : Result<Nothing, R>()
}
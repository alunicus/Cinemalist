package com.github.alunicus.cinemalist.core

sealed class Result<out L, out R> {
    data class Failure<out L : Error>(val error: L) : Result<L, Nothing>()
    data class Success<out R>(val result: R) : Result<Nothing, R>()
}
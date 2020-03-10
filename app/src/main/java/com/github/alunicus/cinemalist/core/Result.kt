package com.github.alunicus.cinemalist.core

sealed class Result<out L, out R> {
    data class Failure<out R : Error>(val error: R) : Result<Nothing, R>()
    data class Success<out L>(val result: L) : Result<L, Nothing>()
}
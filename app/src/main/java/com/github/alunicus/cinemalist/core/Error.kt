package com.github.alunicus.cinemalist.core

sealed class Error {
    object NetworkConnectionError : Error()
    object ServerError : Error()
    abstract class FeatureError : Error()
}
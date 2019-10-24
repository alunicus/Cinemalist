package com.github.alunicus.cinemalist

sealed class Error {
    object NetworkConnectionError : Error()
    object ServerError : Error()
    abstract class FeatureError : Error()
}
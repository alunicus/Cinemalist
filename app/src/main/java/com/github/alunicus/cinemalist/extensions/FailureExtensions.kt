package com.github.alunicus.cinemalist.extensions

import androidx.annotation.IntegerRes
import com.github.alunicus.cinemalist.Error
import com.github.alunicus.cinemalist.R
import com.github.alunicus.cinemalist.Result

@IntegerRes
fun <T : Error> Result.Failure<T>.handleFailure() : Int {
    return when(this.error) {
        is Error.NetworkConnectionError -> R.string.network_connection_error
        is Error.ServerError -> R.string.server_error
        else -> R.string.generic_error
    }
}
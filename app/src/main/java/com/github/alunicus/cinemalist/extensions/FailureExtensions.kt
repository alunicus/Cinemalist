package com.github.alunicus.cinemalist.extensions

import com.github.alunicus.cinemalist.R
import com.github.alunicus.cinemalist.core.Error
import com.github.alunicus.cinemalist.core.Error.NetworkConnectionError
import com.github.alunicus.cinemalist.core.Error.ServerError
import com.github.alunicus.cinemalist.core.ErrorMessage
import com.github.alunicus.cinemalist.core.Result

fun <T : Error> Result.Failure<T>.handleFailure(): ErrorMessage {
    return when (this.error) {
        is NetworkConnectionError -> ErrorMessage(
            R.drawable.ic_no_internet_connection_24dp,
            R.string.network_connection_error
        )
        is ServerError -> ErrorMessage(R.drawable.ic_server_error_24dp, R.string.server_error)
        else -> ErrorMessage(R.drawable.ic_server_error_24dp, R.string.generic_error)
    }
}
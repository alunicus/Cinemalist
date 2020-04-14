package com.github.alunicus.cinemalist.extensions

import android.net.NetworkCapabilities

fun NetworkCapabilities.isOnline(): Boolean = hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        || hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
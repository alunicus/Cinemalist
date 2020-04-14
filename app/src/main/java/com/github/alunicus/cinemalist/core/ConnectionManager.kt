package com.github.alunicus.cinemalist.core

import android.content.Context
import android.net.ConnectivityManager
import com.github.alunicus.cinemalist.extensions.isOnline


class ConnectionManager(context: Context) {
    private val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isConnected() = cm.getNetworkCapabilities(cm.activeNetwork)?.isOnline() ?: false
}
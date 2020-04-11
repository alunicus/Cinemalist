package com.github.alunicus.cinemalist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeForTesting(block: () -> Unit) {
    val observer = Observer<T> { }
    try {
        observeForever(observer)
        block()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        removeObserver(observer)
    }
}
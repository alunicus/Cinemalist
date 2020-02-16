package com.github.alunicus.cinemalist.extensions

import java.util.*

fun Date.asYear(): Int {
    val calendar = Calendar.getInstance(Locale.getDefault())
    calendar.time = this

    return calendar.get(Calendar.YEAR)
}
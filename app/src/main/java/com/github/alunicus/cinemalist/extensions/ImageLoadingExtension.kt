package com.github.alunicus.cinemalist.extensions

import android.widget.ImageView
import coil.api.load

const val BASE_URL_POSTER = "https://image.tmdb.org/t/p/original/"

fun ImageView.loadPoster(url: String) {
    this.load("$BASE_URL_POSTER$url")
}
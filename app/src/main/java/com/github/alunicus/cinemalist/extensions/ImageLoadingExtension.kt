package com.github.alunicus.cinemalist.extensions

import android.content.Context
import android.widget.ImageView
import coil.api.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import com.github.alunicus.cinemalist.R

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original/"

fun ImageView.loadImage(url: String) {
    loadImage(url, R.drawable.movie_placeholder)
}

fun ImageView.loadImage(url: String, onErrorResId: Int) {
    this.load("$BASE_IMAGE_URL$url") {
        crossfade(true)
        error(onErrorResId)
    }
}

fun ImageView.loadBlurredImage(context: Context?, url: String, radius: Float = 10f) {
    context?.let {
        this.load("$BASE_IMAGE_URL$url") {
            crossfade(true)
            transformations(BlurTransformation(context, radius))
        }
    }
}

fun ImageView.loadCircleImage(url: String, onErrorResId: Int) {
    this.load("$BASE_IMAGE_URL$url") {
        crossfade(true)
        transformations(CircleCropTransformation())
        error(onErrorResId)
    }
}
package com.github.alunicus.cinemalist.extensions

import android.content.Context
import android.widget.ImageView
import coil.api.load
import coil.transform.BlurTransformation

const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original/"

fun ImageView.loadImage(url: String) {
    this.load("$BASE_IMAGE_URL$url") {
        crossfade(true)
    }
}

fun ImageView.loadBlurredImage(context: Context?, url: String, radius: Float = 25f) {
    context?.let {
        this.load("$BASE_IMAGE_URL$url") {
            crossfade(true)
            transformations(BlurTransformation(context, radius))
        }
    }
}
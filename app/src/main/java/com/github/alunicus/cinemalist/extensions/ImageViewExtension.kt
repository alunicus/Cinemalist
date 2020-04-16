package com.github.alunicus.cinemalist.extensions

import android.widget.ImageView
import androidx.core.content.ContextCompat

fun ImageView.setResource(resId: Int) {
    setImageDrawable(ContextCompat.getDrawable(context, resId))
}
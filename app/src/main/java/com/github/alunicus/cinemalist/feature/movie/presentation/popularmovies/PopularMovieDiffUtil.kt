package com.github.alunicus.cinemalist.feature.movie.presentation.popularmovies

import androidx.recyclerview.widget.DiffUtil
import com.github.alunicus.cinemalist.feature.movie.domain.model.PopularMovie

class PopularMovieDiffUtil : DiffUtil.ItemCallback<PopularMovie>() {
    override fun areItemsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
        return oldItem.id == newItem.id
                && oldItem.title == newItem.title
    }
}
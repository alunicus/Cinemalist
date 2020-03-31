package com.github.alunicus.cinemalist.feature.movie.presentation.popularmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.alunicus.cinemalist.R
import com.github.alunicus.cinemalist.databinding.ItemPopularMovieBinding
import com.github.alunicus.cinemalist.extensions.loadCircleImage
import com.github.alunicus.cinemalist.extensions.loadImage
import com.github.alunicus.cinemalist.feature.movie.domain.model.PopularMovie

class PopularMoviesAdapter : RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder>() {
    private var _binding: ItemPopularMovieBinding? = null
    private val binding get() = _binding!!

    private val items = mutableListOf<PopularMovie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val movie = items[adapterPosition]

            name.text = movie.title
            poster.loadImage(movie.posterPath, R.drawable.movie_placeholder)
        }
    }

    fun setItems(list: List<PopularMovie>) {
        items.clear()
        items.addAll(list)

        notifyDataSetChanged()
    }

    class ViewHolder(binding: ItemPopularMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val poster = binding.popularMoviePoster
        val name = binding.popularMovieName
    }
}
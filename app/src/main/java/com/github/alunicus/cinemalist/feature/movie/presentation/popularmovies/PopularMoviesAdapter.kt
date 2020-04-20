package com.github.alunicus.cinemalist.feature.movie.presentation.popularmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.alunicus.cinemalist.R
import com.github.alunicus.cinemalist.databinding.ItemPopularMovieBinding
import com.github.alunicus.cinemalist.extensions.loadImage
import com.github.alunicus.cinemalist.feature.movie.domain.model.PopularMovie

class PopularMoviesAdapter(private val onClick: (movieId: Int) -> Unit) :
    PagedListAdapter<PopularMovie, PopularMoviesAdapter.ViewHolder>(PopularMovieDiffUtil()) {

    private var _binding: ItemPopularMovieBinding? = null
    private val binding get() = _binding!!

    private val items = mutableListOf<PopularMovie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemPopularMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onClick) }
    }

    fun setItems(list: List<PopularMovie>) {
        items.clear()
        items.addAll(list)

        notifyDataSetChanged()
    }

    class ViewHolder(binding: ItemPopularMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        private val poster = binding.popularMoviePoster
        private val name = binding.popularMovieName

        fun bind(movie: PopularMovie, onClick: (movieId: Int) -> Unit) {
            movie.apply {
                name.text = title
                poster.loadImage(posterPath, R.drawable.movie_placeholder)

                itemView.setOnClickListener { onClick(id) }
            }
        }
    }
}
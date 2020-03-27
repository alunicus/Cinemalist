package com.github.alunicus.cinemalist.feature.movie.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.github.alunicus.cinemalist.R
import com.github.alunicus.cinemalist.databinding.MovieFragmentBinding
import com.github.alunicus.cinemalist.extensions.asYear
import com.github.alunicus.cinemalist.extensions.loadBlurredImage
import com.github.alunicus.cinemalist.extensions.loadImage
import com.github.alunicus.cinemalist.extensions.toDuration
import com.github.alunicus.cinemalist.feature.movie.domain.model.Duration
import com.github.alunicus.cinemalist.feature.movie.domain.model.Movie
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private var _binding: MovieFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModel()

    private val castAdapter: CastAdapter =
        CastAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initToolbar()

        viewModel.onMovieLoaded().observe(viewLifecycleOwner) {
            populateView(it)
        }

        viewModel.loadMovie(458156)

        binding.movieCastList.adapter = castAdapter
    }

    private fun initToolbar() {
        val compatActivity = (activity as AppCompatActivity)

        compatActivity.setSupportActionBar(binding.movieToolbar)

        compatActivity.supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayShowTitleEnabled(false)
        }
    }

    private fun populateView(movie: Movie) {
        binding.apply {
            movie.apply {
                moviePoster.loadImage(movieDetails.posterPath)
                movieBackdrop.loadBlurredImage(context, movieDetails.backdropPath)
                movieTitle.text = movieDetails.title
                movieYear.text = movieDetails.releaseDate.asYear().toString()
                movieOverview.text = movieDetails.overview
                movieDuration.text = getDuration(movieDetails.runtime.toDuration())
                movieRating.text = movieDetails.voteAverage.toString()
                movieVoteCount.text = movieDetails.voteCount.toString()

                castAdapter.setItems(cast)
            }

            movieAddToList.setOnClickListener {
                Toast.makeText(
                    context,
                    R.string.movie_add_to_list_message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            movieAddToFavorite.setOnClickListener {
                it.isSelected = !it.isSelected
            }
        }
    }

    private fun getDuration(duration: Duration): String {
        return getString(
            R.string.movie_duration,
            duration.hours.toString(),
            duration.minutes.toString()
        )
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}
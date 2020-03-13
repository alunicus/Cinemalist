package com.github.alunicus.cinemalist.feature.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.github.alunicus.cinemalist.R
import com.github.alunicus.cinemalist.data.Duration
import com.github.alunicus.cinemalist.data.Movie
import com.github.alunicus.cinemalist.databinding.MovieFragmentBinding
import com.github.alunicus.cinemalist.extensions.asYear
import com.github.alunicus.cinemalist.extensions.loadBlurredImage
import com.github.alunicus.cinemalist.extensions.loadImage
import com.github.alunicus.cinemalist.extensions.toDuration
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private var _binding: MovieFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModel()

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
            moviePoster.loadImage(movie.posterPath)
            movieBackdrop.loadBlurredImage(context, movie.backdropPath)
            movieTitle.text = movie.title
            movieYear.text = movie.releaseDate.asYear().toString()
            movieOverview.text = movie.overview
            movieDuration.text = getDuration(movie.runtime.toDuration())
            movieRating.text = movie.voteAverage.toString()
            movieVoteCount.text = movie.voteCount.toString()
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
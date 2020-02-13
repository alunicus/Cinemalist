package com.github.alunicus.cinemalist.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.alunicus.cinemalist.data.Movie
import com.github.alunicus.cinemalist.databinding.MovieFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    companion object {
        fun newInstance() = MovieFragment()
    }

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

        viewModel.onMovieLoaded().observe(viewLifecycleOwner, Observer {
            populateView(it)
        })

        viewModel.loadMovie(458156)
    }

    private fun populateView(movie: Movie) {
        binding.apply {
            movieTitle.text = movie.title
            movieYear.text = movie.releaseDate
            movieDuration.text = movie.runtime.toString()
            movieOverview.text = movie.overview
        }
    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }
}
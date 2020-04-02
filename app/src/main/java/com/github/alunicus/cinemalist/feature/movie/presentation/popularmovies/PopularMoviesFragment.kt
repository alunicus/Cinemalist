package com.github.alunicus.cinemalist.feature.movie.presentation.popularmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.github.alunicus.cinemalist.databinding.PopularMoviesFragmentBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class PopularMoviesFragment : Fragment() {

    private var _binding: PopularMoviesFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PopularMoviesViewModel by viewModel()

    private val adapter = PopularMoviesAdapter() {
        val direction = PopularMoviesFragmentDirections.actionHomeToMovie(it)
        findNavController().navigate(direction)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PopularMoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.popularMoviesList.adapter = adapter

        viewModel.onPopularMoviesLoaded().observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

        viewModel.onError().observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
        }

        viewModel.loadPopularMovies()
    }
}

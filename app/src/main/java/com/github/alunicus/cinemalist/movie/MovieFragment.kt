package com.github.alunicus.cinemalist.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.alunicus.cinemalist.R
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    companion object {
        fun newInstance() = MovieFragment()
    }

    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
package com.github.alunicus.cinemalist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.alunicus.cinemalist.movie.MovieRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieRepository: MovieRepository by inject()

        GlobalScope.launch {
            try {
                movieRepository.getMovieById(299536).apply {
                    Log.d("TAG", "ID: $id and Title: $title")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
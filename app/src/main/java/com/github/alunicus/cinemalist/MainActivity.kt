package com.github.alunicus.cinemalist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationUI.setupWithNavController(
            findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation),
            Navigation.findNavController(this, R.id.activity_main_host_fragment)
        )
    }
}